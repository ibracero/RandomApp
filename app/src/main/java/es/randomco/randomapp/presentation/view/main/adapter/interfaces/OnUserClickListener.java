package es.randomco.randomapp.presentation.view.main.adapter.interfaces;

import es.randomco.randomapp.domain.entities.User;

public interface OnUserClickListener {

    void onUserClick(User user);

    void onUserChangeFavorite(User user);

    void onDeleteUser(User user);
}
