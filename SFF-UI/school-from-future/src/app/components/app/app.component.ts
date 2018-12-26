import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'School From Future';

  
  isLogged() {
    return sessionStorage.getItem('user')!==null;
  }
}
