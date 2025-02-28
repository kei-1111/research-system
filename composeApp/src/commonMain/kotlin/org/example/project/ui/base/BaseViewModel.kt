package org.example.project.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Suppress("VariableNaming")
abstract class BaseViewModel<S : UiState, E : UiEvent>(
    initialState: S,
) : ViewModel() {
    protected val _uiState = MutableStateFlow<S>(initialState)
    val uiState: StateFlow<S> = _uiState.asStateFlow()

    protected val _uiEvent = MutableSharedFlow<E>()
    val uiEvent: SharedFlow<E> = _uiEvent

    fun updateUiState(update: (S) -> S) {
        _uiState.update { update(it) }
    }

    fun onEvent(event: E) {
        viewModelScope.launch { _uiEvent.emit(event) }
    }
}
