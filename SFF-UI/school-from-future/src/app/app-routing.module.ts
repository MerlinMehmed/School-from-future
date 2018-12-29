import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AppComponent } from './components/app/app.component';
import { AddSubjectComponent } from './components/add-subject/add-subject.component';
import { IndexComponent } from './components/index/index.component';
import { AddGradeComponent } from './components/add-grade/add-grade.component';
import { EventComponent } from './components/event/event.component';
import { StudentProfileComponent } from './components/student-profile/student-profile.component';

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
    path: 'register',
    component: RegisterComponent
  }, {
    path: 'add-subject',
    component: AddSubjectComponent
  }, {
    path: 'grade-student',
    component: AddGradeComponent
  }, {
    path: 'event',
    component: EventComponent
  }, {
    path: 'student-profile',
    component: StudentProfileComponent
  }];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
