import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AgmCoreModule } from '@agm/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule, MatCheckboxModule, MatIconModule, MatListModule, MatFormFieldModule, MatCardModule, MatInputModule } from '@angular/material';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/app/app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AddSubjectComponent } from './components/add-subject/add-subject.component';
import { IndexComponent } from './components/index/index.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { AddGradeComponent } from './components/add-grade/add-grade.component';
import { EventComponent } from './components/event/event.component';
import { ChatComponent } from './components/chat/chat.component';
import { StudentProfileComponent } from './components/student-profile/student-profile.component';
import { AllSubjectsComponent } from './components/all-subjects/all-subjects.component';
import { SubjectViewComponent } from './components/subject-view/subject-view.component';
import { SubjectEventsComponent } from './components/subject-events/subject-events.component';

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
    ChatComponent,
    StudentProfileComponent,
    AllSubjectsComponent,
    SubjectViewComponent,
    SubjectEventsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    AgmCoreModule.forRoot({
      apiKey: ''
    }),
    BrowserAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    MatIconModule,
    MatListModule,
    MatFormFieldModule,
    MatCardModule,
    MatInputModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
