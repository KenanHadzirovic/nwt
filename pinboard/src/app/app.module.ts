import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'
import { NgbModule, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AngularFontAwesomeModule } from 'angular-font-awesome';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WorkspaceComponent } from './components/workspace/workspace.component';
import { OptionsComponent } from './components/options/options.component';
import { LoginComponent } from './components/login/login.component';
import { PostComponent } from './components/post/post.component';
import { HeaderComponent } from './components/header/header.component';
import { AddNoteComponent } from './components/modals/add-note/add-note/add-note.component';
import { OptionComponent } from './components/options/option/option.component';

@NgModule({
  declarations: [
    AppComponent,
    WorkspaceComponent,
    OptionsComponent,
    LoginComponent,
    PostComponent,
    HeaderComponent,
    AddNoteComponent,
    OptionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AngularFontAwesomeModule,
    NgbModule.forRoot()
  ],
  entryComponents: [
    AddNoteComponent
  ],
  providers: [
    NgbActiveModal
  ],
  bootstrap: [AppComponent, HeaderComponent]
})
export class AppModule { }
