import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AddSubjectComponent } from './components/add-subject/add-subject.component';
import { IndexComponent } from './components/index/index.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/index',
    pathMatch: 'full'
  }, {
    path: 'index',
    component: IndexComponent
  }, {
    path: 'login',
    component: LoginComponent
  }, {
    path: 'add-subject',
    component: AddSubjectComponent
  }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
