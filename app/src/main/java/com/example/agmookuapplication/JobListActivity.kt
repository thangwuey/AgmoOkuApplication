package com.example.agmookuapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class JobListActivity : AppCompatActivity() {

    private lateinit var jobRef: DatabaseReference
    private lateinit var jobRecyclerView: RecyclerView
    private lateinit var jobArray: ArrayList<JobsClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_list)

        jobRecyclerView = findViewById(R.id.recycler_jobs)
        jobRecyclerView.layoutManager = LinearLayoutManager(this)
        jobRecyclerView.setHasFixedSize(true)

        jobArray = arrayListOf<JobsClass>()

        getJobInfo()
    }

    private fun getJobInfo() {
        jobRef = FirebaseDatabase.getInstance().getReference("Jobs")

        jobRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {

                        val job = userSnapshot.getValue(JobsClass::class.java)
                        jobArray.add(job!!)
                    }
                    jobRecyclerView.adapter = JobsViewHolder(jobArray,this@JobListActivity)

                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}