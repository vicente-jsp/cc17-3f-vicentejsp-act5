package com.example.artspaceapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    data class Artwork(val imageResId: Int, val title: String, val artist: String)

    private val artworks = listOf(
        Artwork(R.drawable.starry, "The Starry Night", "Vincent Van Gogh, 1889"),
        Artwork(R.drawable.mona, "Mona Lisa", "Leonardo da Vinci"),
        Artwork(R.drawable.venus, "The birth of Venus", "Sandro Botticelli, 1486"),
        Artwork(R.drawable.wanderer, "Wanderer above the Sea of Fog", "Caspar David Friedrich, 1818"),
        Artwork(R.drawable.luncheon, "Luncheon of the Boating Party", "Pierre-Auguste Renoir, 1881"),
        Artwork(R.drawable.kiss, "The Kiss", "Gustav Klimt, 1908"),
        Artwork(R.drawable.girl, "Girl with a Pearl Earring", "Johannes Vermeer, 1665")
    )

    private var currentArtworkIndex = 0

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("artwork_index", currentArtworkIndex)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentArtworkIndex = savedInstanceState.getInt("artwork_index", 0)
        updateArtwork(findViewById(R.id.imageView), findViewById(R.id.title), findViewById(R.id.author))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val artworkImage: ImageView = findViewById(R.id.imageView)
        val artworkTitle: TextView = findViewById(R.id.title)
        val artworkArtist: TextView = findViewById(R.id.author)
        val prevButton: Button = findViewById(R.id.prevbutton)
        val nextButton: Button = findViewById(R.id.nextbutton)

        updateArtwork(artworkImage, artworkTitle, artworkArtist)

        prevButton.setOnClickListener {
            currentArtworkIndex = if (currentArtworkIndex == 0) {
                artworks.size - 1
            } else {
                currentArtworkIndex - 1
            }
            updateArtwork(artworkImage, artworkTitle, artworkArtist)
        }
        nextButton.setOnClickListener {
            currentArtworkIndex = if (currentArtworkIndex == artworks.size - 1) {
                0
            } else {
                currentArtworkIndex + 1
            }
            updateArtwork(artworkImage, artworkTitle, artworkArtist)
        }
    }
        private fun updateArtwork(imageView: ImageView, titleView: TextView, artistView: TextView) {
            val artwork = artworks[currentArtworkIndex]
            imageView.setImageResource(artwork.imageResId)
            titleView.text = "${artwork.title}"
            artistView.text = "${artwork.artist}"
        }
    }


