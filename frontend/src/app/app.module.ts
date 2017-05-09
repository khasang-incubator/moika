import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import {WashBoxComponent} from './components/wash-box.component';
import {WashBoxService} from './services/wash-box.service';
import { WashFacilityComponent } from './components/wash-facility.component';
import {WashFacilityService} from './services/wash-facility.service';
import {ClientService} from "./services/client.service";
import { ClientComponent } from './components/client/client.component';
import { ClientListComponent } from './components/client/client-list.component';
import {CrudService} from "./services/crud.service";
import {BsDropdownModule} from "ngx-bootstrap";

@NgModule({
  declarations: [
    AppComponent,
    WashBoxComponent,
    WashFacilityComponent,
    ClientComponent,
    ClientListComponent
  ],
  imports: [
    BsDropdownModule.forRoot(),
    NgbModule.forRoot(),
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [WashBoxService, WashFacilityService, ClientService, CrudService],
  bootstrap: [AppComponent]
})
export class AppModule { }
