package com.example.shoplistkotlin.presentation.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplistkotlin.SecondActivity
import com.example.shoplistkotlin.databinding.ActivityMainBinding
import com.example.shoplistkotlin.domain.ShopItem
import com.example.shoplistkotlin.presentation.ShopListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var shopListAdapter: ShopListAdapter
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setupResult()

        viewModel.shopList.observe(this) {
            shopListAdapter.submitList(it)
        }

        setupRecycler()

    }

    private fun setupRecycler() {
        with(binding.rvShopList) {
            shopListAdapter = ShopListAdapter()
            adapter = shopListAdapter

            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_ENABLE,
                ShopListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_DISABLE,
                ShopListAdapter.MAX_POOL_SIZE
            )
        }
        setupListeners()
        setupSwipeListener(binding.rvShopList)
    }

    private fun setupSwipeListener(recycler: RecyclerView) {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.currentList[viewHolder.adapterPosition - 1]
                viewModel.deleteShopItem(item)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recycler)
    }

    private fun setupListeners() {
        shopListAdapter.onShopItemLongClickListener = {
            viewModel.changeEnableState(it)
        }

        shopListAdapter.onShopItemClickListener = { shopItem, position ->
            Intent(this, SecondActivity::class.java).apply {
                putExtra(EDIT_NAME, shopItem.name)
                putExtra(EDIT_COUNT, shopItem.count.toString())
                startActivity(this)
            }

        }

        binding.fab.setOnClickListener {
            resultLauncher.launch(Intent(this, SecondActivity::class.java))
        }
    }

    private fun setupResult() {

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == RESULT_OK && result.data != null) {

                    val shopItem = ShopItem(
                        count = result.data!!.getStringExtra(SecondActivity.COUNT)!!.toInt(),
                        name = result.data!!.getStringExtra(SecondActivity.NAME).toString(),
                        enable = true
                    )
                    viewModel.addItem(shopItem)
                }
            }
    }

    companion object {
        const val EDIT_NAME = "EDIT_NAME"
        const val EDIT_COUNT = "EDIT_COUNT"
    }
}