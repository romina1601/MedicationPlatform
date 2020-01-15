import { Injectable } from '@angular/core';

/** Local Storage Service Class
 * 
 * login.component.ts depends on it
*/
@Injectable()
export class StorageService {
  public app_key = 'online-medication-platform';

  public app_token = `${this.app_key}_token`;
  public role_token = 'role';
  

  /**
   * Get app key from local storage
   * @param  {string} key key name
   * @return {string}
   */
  public get(key: string): string {
    const sessionValue = localStorage.getItem(key);
    return sessionValue;
  }

  /**
   * Set a key in the local storage
   * @param  {string} key   key name
   * @param  {string} value key value
   * @return {string|false}
   */
  public set(key: string, value: string): string | false {
    localStorage.setItem(key, value);
    try {
      return this.get(key);
    } catch (error) {
      return false;
    }
  }

  /**
   * Remove key from local storage
   * @param  {string} key key name
   * @return {boolean}
   */
  public remove(key: string): boolean {
    localStorage.removeItem(key);
    return !this.get(key) ? true : false;
  }
}
