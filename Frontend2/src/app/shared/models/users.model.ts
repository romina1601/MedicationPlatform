import { Injectable } from '@angular/core';
import { UsersInterface } from './interfaces/users';

@Injectable()
export class UsersModel {
  /** will grab the list of all Users */
  all: Array<UsersInterface>;

  /** will grab the id value of the selected User */
  selectedUsername: string;

  setSelected(username: string) {
    return this.all.find((users: UsersInterface) => users.username === username);
  }

  removeSelected() {
    this.selectedUsername = undefined;
  }

  clear() {
    this.removeSelected();
    this.all = undefined;
  }
}