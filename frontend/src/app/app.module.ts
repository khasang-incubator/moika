import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import {ButtonModule} from 'primeng/primeng';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/common/header/header.component';
import { LadingComponent } from './components/common/lading/lading.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LadingComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
