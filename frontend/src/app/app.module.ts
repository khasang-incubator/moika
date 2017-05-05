import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import {WashBoxComponent} from './app.washbox.component';
import {WashBoxService} from './app.washbox.service';
import { WashFacilityComponent } from './wash-facility.component';
import {WashFacilityService} from './wash-facility.service';

@NgModule({
  declarations: [
    AppComponent,
    WashBoxComponent,
    WashFacilityComponent
  ],
  imports: [
    NgbModule.forRoot(),
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [WashBoxService, WashFacilityService],
  bootstrap: [AppComponent]
})
export class AppModule { }
