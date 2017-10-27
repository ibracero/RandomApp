package es.randomco.randomapp.presentation.presenter;

public interface Presenter<V> {

    void attachView(V view);

    void detachView();
}
