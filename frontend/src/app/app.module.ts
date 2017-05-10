import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import {WashBoxComponent} from './components/facility/wash-box.component';
import {WashBoxService} from './services/wash-box.service';
import { WashFacilityComponent } from './components/facility/wash-facility.component';
import {WashFacilityService} from './services/wash-facility.service';
import {ClientService} from "./services/client.service";
import { ClientComponent } from './components/client/client.component';
import { ClientListComponent } from './components/client/client-list.component';
import {CrudService} from "./services/crud.service";
import {BsDropdownModule} from "ngx-bootstrap";
import { NavbarComponent } from './components/navbar/navbar.component';
import { PhoneListComponent } from './components/client/phone-list.component';
import { FacilityDetailComponent } from './components/facility/facility-detail.component';
import { WashBoxDetailComponent } from './components/facility/wash-box-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    WashBoxComponent,
    WashFacilityComponent,
    ClientComponent,
    ClientListComponent,
    NavbarComponent,
    PhoneListComponent,
    FacilityDetailComponent,
    WashBoxDetailComponent
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
