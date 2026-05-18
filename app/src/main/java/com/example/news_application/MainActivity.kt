package com.example.news_application

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news_application.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupRecyclerView()
        setupUI()
        loadNews()
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.recycleView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsAdapter
        }
    }

    private fun setupUI() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            loadNews()
        }

        binding.btnRetry.setOnClickListener {
            loadNews()
        }
    }

    private fun loadNews() {
        lifecycleScope.launch {
            showLoading()
            try {
                val response = NewsClient.apiService.getHeadlines()
                if (response.isSuccessful) {
                    val news_responce = response.body()
                    val news_list = news_responce?.articles ?: emptyList()
                    newsAdapter.submitList(news_list)
                    val hasNews = news_list.isNotEmpty()
                    showContent(hasNews)
                } else {
                    showError("Ошибка сервера: ${response.code()}")
                }
            } catch (e: Exception) {
                showError("Ошибка сети: ${e.localizedMessage}")
            } finally {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.layoutError.visibility = View.GONE
        binding.errorText.visibility = View.GONE
        binding.layoutEmpty.visibility = View.GONE
    }

    private fun showContent(isEmpty: Boolean) {
        binding.progressBar.visibility = View.GONE
        binding.layoutError.visibility = View.GONE
        binding.errorText.visibility = View.GONE
        binding.layoutEmpty.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }

    private fun showError(message: String) {
        binding.progressBar.visibility = View.GONE
        binding.layoutError.visibility = View.VISIBLE
        binding.layoutEmpty.visibility = View.GONE
        binding.errorText.text = message
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}