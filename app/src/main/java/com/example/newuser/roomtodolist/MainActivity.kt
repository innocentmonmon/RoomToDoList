package com.example.newuser.roomtodolist

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.d
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_task.view.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    lateinit var taskAdapter: AppAdapter
    val tasks: MutableList<RoomTask> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        task_recycler.layoutManager = LinearLayoutManager(MainActivity@ this)
        task_recycler.setHasFixedSize(true)
        taskAdapter = AppAdapter(tasks)
        task_recycler.adapter = taskAdapter

        val db = AppDatabase.getDatabase(applicationContext)
        doAsync {
            d("BY_ID", db.taskDao().getById(1).toString())
            tasks.addAll(db.taskDao().getAll())
            uiThread {
                taskAdapter.notifyDataSetChanged()
            }
        }
        fab.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_task, null)
            val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setTitle("New Task")
            val mAlertDialog = mBuilder.show()
            mDialogView.btnDone.setOnClickListener {
                mAlertDialog.dismiss()
                var newTaskID = mDialogView.txt_taskID.text.toString().toInt()
                var newTask = mDialogView.txt_task.text
                tasks.add(RoomTask(newTaskID, newTask.toString()))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
