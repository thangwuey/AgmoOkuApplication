package com.example.agmookuapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso

class JobsDetails : AppCompatActivity() {

    private lateinit var jobDetailsImage: ImageView
    private lateinit var jobDetailsName: TextView
    private lateinit var jobDetailsTime: TextView
    private lateinit var jobDetailsStatus: TextView
    private lateinit var jobDetailsSalary: TextView
    private lateinit var jobDetailsDescription: TextView

    private lateinit var contactButton: Button

    private lateinit var detailsDb: FirebaseDatabase
    private lateinit var detailsRef : DatabaseReference
    private lateinit var storageRef: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jobs_details)

        jobDetailsImage = findViewById(R.id.jobs_image)

        jobDetailsName = findViewById(R.id.jobs_name)
        jobDetailsTime = findViewById(R.id.jobs_time)
        jobDetailsStatus = findViewById(R.id.jobs_status)
        jobDetailsSalary = findViewById(R.id.jobs_salary)
        jobDetailsDescription = findViewById(R.id.jobs_description)

        contactButton = findViewById(R.id.contactButton)

        val intent = getIntent()
        val jobid = intent.getStringExtra("jobID")

        detailsDb = FirebaseDatabase.getInstance()
        detailsRef = detailsDb.reference.child("Jobs").child(jobid!!)
        storageRef = FirebaseStorage.getInstance().reference

        displayJobInfo()

        contactButton.setOnClickListener {

            val email = ""
            val subject = ""
            val message = ""

            val intentemail = Intent(Intent.ACTION_SENDTO).apply {

                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, email)
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, message)

            }

            try{
                startActivity(intentemail)
            }
            catch (e: Exception){
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayJobInfo() {

        detailsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists())
                {
                    val jobImage = snapshot.child("jobImageUrl").value.toString()
                    Picasso.get().load(jobImage).into(jobDetailsImage)

                    //fol firebase and the class declear name
                    jobDetailsName.text = snapshot.child("jobName").value.toString()
                    jobDetailsTime.text = snapshot.child("workingTime").value.toString()
                    jobDetailsStatus.text = snapshot.child("workingStatus").value.toString()
                    jobDetailsSalary.text = snapshot.child("monthlySalary").value.toString()
                    jobDetailsDescription.text = snapshot.child("jobDescription").value.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}