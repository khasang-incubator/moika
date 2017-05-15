import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { WashBoxComponent} from './components/facility/wash-box.component';
import { WashBoxService} from './model/services/wash-box.service';
import { WashFacilityComponent } from './components/facility/wash-facility.component';
import { WashFacilityService} from './model/services/wash-facility.service';
import { ClientService} from "./model/services/client.service";
import { ClientComponent } from './components/client/client.component';
import { ClientListComponent } from './components/client/client-list.component';
import { CrudService} from "./model/services/crud.service";
import { BsDropdownModule} from "ngx-bootstrap";
import { NavbarComponent } from './components/shared/navbar/navbar.component';
import { PhoneListComponent } from './components/client/phone-list.component';
import { FacilityDetailComponent } from './components/facility/facility-detail.component';
import { WashBoxDetailComponent } from './components/facility/wash-box-detail.component';
import { AppAboutComponent } from './components/shared/common/app-about.component';
import { NotFoundComponent } from './components/shared/common/not-found.component';
import { HomePageComponent } from './components/shared/common/home-page.component';
import { RouterModule, Routes } from "@angular/router";
import { MainOutletComponent } from './components/main-outlet.component';
import {DataTableModule, DropdownModule, InputTextareaModule, PanelModule, SharedModule} from 'primeng/primeng';
import { CommonTypeComponent } from './components/reference/common-type.component';
import { CommonTypeSelectorComponent } from './components/reference/common-type-selector.component';
import {NoopAnimationsModule} from "@angular/platform-browser/animations";

// определение маршрутов
const appRoutes: Routes =[
  { path: '', redirectTo: '/start', pathMatch: 'full'},
  { path: 'start', component: HomePageComponent},
  { path: 'about', component: AppAboutComponent},
  { path: 'clients', component: ClientListComponent},
  { path: 'washFacilities', component: WashFacilityComponent},
  { path: 'refs', component: CommonTypeSelectorComponent},
  { path: '**', component: NotFoundComponent },
];

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
    WashBoxDetailComponent,
    AppAboutComponent,
    NotFoundComponent,
    HomePageComponent,
    MainOutletComponent,
    CommonTypeComponent,
    CommonTypeSelectorComponent,
  ],
  imports: [
    BsDropdownModule.forRoot(),
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    FormsModule,
    HttpModule,
    DataTableModule, SharedModule,  DataTableModule, InputTextareaModule, FormsModule, PanelModule, DropdownModule, NoopAnimationsModule
  ],
  providers: [WashBoxService, WashFacilityService, ClientService, CrudService],
  bootstrap: [AppComponent]
})
export class AppModule { }
