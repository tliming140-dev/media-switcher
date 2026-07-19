package com.tliming.mediaswitcher

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.tliming.mediaswitcher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    data class AppEntry(
        val packageName: String,
        val displayName: String,
        val subtitle: String,
        val downloadUrl: String? = null,
        val emoji: String
    )

    private val apps = listOf(
        AppEntry(
            packageName = "com.predidit.kazumi",
            displayName = "番剧",
            subtitle = "Kazumi · 追番 & 流媒体",
            emoji = "📺",
            downloadUrl = "https://github.com/Predidit/Kazumi/releases"
        ),
        AppEntry(
            packageName = "com.github.wgh136.venera",
            displayName = "漫画",
            subtitle = "Venera · 漫画阅读器",
            emoji = "📚",
            downloadUrl = "https://github.com/venera-app/venera/releases"
        ),
        AppEntry(
            packageName = "com.huangusaki.easycopy",
            displayName = "拷贝漫画",
            subtitle = "EasyCopy · 拷贝漫画第三方",
            emoji = "📖",
            downloadUrl = null
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCard(binding.cardKazumi, apps[0])
        setupCard(binding.cardVenera, apps[1])
        setupCard(binding.cardEasycopy, apps[2])
    }

    private fun setupCard(card: MaterialCardView, entry: AppEntry) {
        card.setOnClickListener {
            launchApp(entry)
        }
    }

    private fun launchApp(entry: AppEntry) {
        val intent = packageManager.getLaunchIntentForPackage(entry.packageName)
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        } else {
            // App 未安装
            val msg = "${entry.displayName} 未安装"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

            // 如果有下载链接,提示跳转到下载页
            if (entry.downloadUrl != null) {
                val downloadIntent = Intent(Intent.ACTION_VIEW, Uri.parse(entry.downloadUrl))
                try {
                    startActivity(downloadIntent)
                } catch (_: Exception) {
                    Toast.makeText(this, "请先安装 ${entry.displayName}", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "请先安装 ${entry.displayName}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
