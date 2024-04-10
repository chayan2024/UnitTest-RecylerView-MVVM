package com.example.unittest_recylerview_mvvm.viewmodel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class ItemViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var observer: Observer<List<String>>

    private lateinit var viewModel: ItemViewModel

    @Before
    fun setup() {
        viewModel = ItemViewModel()
    }

    @Test
    fun `test initial state`() {
        viewModel.items.observeForever(observer)
        Mockito.verify(observer).onChanged(listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5"))
        viewModel.items.removeObserver(observer)
    }

    @Test
    fun `test adding item`() {
        viewModel.items.observeForever(observer)
        viewModel.addItem("New Item")
        assertEquals(viewModel.items.value?.size, 6)
        Mockito.verify(observer).onChanged(listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "New Item"))
        viewModel.items.removeObserver(observer)
    }

    @Test
    fun `test adding multiple items`() {
        viewModel.items.observeForever(observer)
        viewModel.addItem("New Item 1")
        viewModel.addItem("New Item 2")
        viewModel.addItem("New Item 3")
        assertEquals(viewModel.items.value?.size, 8)
        Mockito.verify(observer).onChanged(
            listOf(
                "Item 1", "Item 2", "Item 3", "Item 4", "Item 5",
                "New Item 1", "New Item 2", "New Item 3"
            )
        )
        viewModel.items.removeObserver(observer)
    }

    @Test
    fun `test adding empty item`() {
        viewModel.items.observeForever(observer)
        viewModel.addItem("")
        assertEquals(viewModel.items.value?.size, 6)
        viewModel.items.removeObserver(observer)
    }

}
