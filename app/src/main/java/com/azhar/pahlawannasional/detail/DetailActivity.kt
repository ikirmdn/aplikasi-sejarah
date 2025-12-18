package com.azhar.pahlawannasional.detail

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.azhar.pahlawannasional.R
import com.azhar.pahlawannasional.databinding.ActivityDetailBinding
import com.azhar.pahlawannasional.main.ModelMain
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    lateinit var nama: String
    lateinit var nama_lengkap: String
    lateinit var kategori: String
    lateinit var asal: String
    lateinit var lahir: String
    lateinit var usia: String
    lateinit var gugur: String
    lateinit var lokasimakam: String
    lateinit var history: String
    lateinit var modelMain: ModelMain
    private lateinit var binding: ActivityDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set transparent statusbar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        setSupportActionBar(binding.toolbar)
        assert(supportActionBar != null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //get data intent
        modelMain = intent.getSerializableExtra(DETAIL_PAHLAWAN) as ModelMain
        if (modelMain != null) {
            nama = modelMain.nama
            nama_lengkap = modelMain.namaLengkap
            kategori = modelMain.kategori
            asal = modelMain.asal
            lahir = modelMain.lahir
            usia = modelMain.usia
            gugur = modelMain.gugur
            lokasimakam = modelMain.lokasimakam
            history = modelMain.history

            Glide.with(this)
                .load(modelMain.image)
                .into(binding.imagePahlawan)

            binding.tvNamaPahlawan.setText(nama)
            binding.tvNamaLengkap.setText(nama_lengkap)
            binding.tvKategori.setText(kategori)
            binding.tvAsal.setText(asal)
            binding.tvUsia.setText(usia)
            binding.tvLahir.setText("Lahir : $lahir")
            binding.tvWafat.setText("Wafat : $gugur")
            binding.tvMakam.setText("Lokasi Makam : $lokasimakam")
            binding.tvRiwayat.setText("Riwayat : $history")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val DETAIL_PAHLAWAN = "DETAIL_PAHLAWAN"
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val window = activity.window
            val layoutParams = window.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            window.attributes = layoutParams
        }
    }

}