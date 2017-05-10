import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { WashBoxComponent} from './components/facility/wash-box.component';
import { WashBoxService} from './services/wash-box.service';
import { WashFacilityComponent } from './components/facility/wash-facility.component';
import { WashFacilityService} from './services/wash-facility.service';
import { ClientService} from "./services/client.service";
import { ClientComponent } from './components/client/client.component';
import { ClientListComponent } from './components/client/client-list.component';
import { CrudService} from "./services/crud.service";
import { BsDropdownModule} from "ngx-bootstrap";
import { NavbarComponent } from './components/navbar/navbar.component';
import { PhoneListComponent } from './components/client/phone-list.component';
import { FacilityDetailComponent } from './components/facility/facility-detail.component';
import { WashBoxDetailComponent } from './components/facility/wash-box-detail.component';
import { AppAboutComponent } from './components/common/app-about.component';
import { NotFoundComponent } from './components/common/not-found.component';
import { HomePageComponent } from './components/common/home-page.component';
import { RouterModule, Routes } from "@angular/router";

// определение маршрутов
const appRoutes: Routes =[
  { path: '', redirectTo: '/start', pathMatch: 'full'},
  { path: 'start', component: HomePageComponent},
  { path: 'about', component: AppAboutComponent},
  { path: '**', component: NotFoundComponent },
  { path: 'clients', component: ClientListComponent},
  { path: 'washFacilities', component: WashFacilityComponent}
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
    HomePageComponent
  ],
  imports: [
    BsDropdownModule.forRoot(),
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [WashBoxService, WashFacilityService, ClientService, CrudService],
  bootstrap: [AppComponent]
})
export class AppModule { }
