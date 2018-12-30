import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AgmCoreModule } from '@agm/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/app/app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AddSubjectComponent } from './components/add-subject/add-subject.component';
import { IndexComponent } from './components/index/index.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { AddGradeComponent } from './components/add-grade/add-grade.component';
import { EventComponent } from './components/event/event.component';
import { StudentProfileComponent } from './components/student-profile/student-profile.component';
import { AllSubjectsComponent } from './components/all-subjects/all-subjects.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AddSubjectComponent,
    IndexComponent,
    NavBarComponent,
    AddGradeComponent,
    EventComponent,
	StudentProfileComponent,
	AllSubjectsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    AgmCoreModule.forRoot({
      apiKey: ''
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
