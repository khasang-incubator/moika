import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { WashBoxComponent} from './components/facility/wash-box-list.component';
import { WashBoxService} from './model/services/wash-box.service';
import { WashFacilityListComponent } from './components/facility/wash-facility-list.component';
import { WashFacilityService} from './model/services/wash-facility.service';
import { ClientService} from "./model/services/client.service";
import { ClientComponent } from './components/client/client.component';
import { ClientListComponent } from './components/client/client-list.component';
import { CrudService} from "./model/services/crud.service";
import { BsDropdownModule} from "ngx-bootstrap";
import { AdminNavbarComponent } from './components/shared/navbar/admin/admin-navbar.component';
import { PhoneListComponent } from './components/client/phone-list.component';
import { FacilityDetailComponent } from './components/facility/facility-detail.component';
import { WashBoxDetailComponent } from './components/facility/wash-box-detail.component';
import { AppAboutComponent } from './components/shared/common/app-about.component';
import { NotFoundComponent } from './components/shared/common/not-found.component';
import { HomePageComponent } from './components/shared/common/home-page.component';
import { MainOutletComponent } from './components/main-outlet.component';
import {
  DataTableModule, DropdownModule, InputTextareaModule, PanelModule, DialogModule, SharedModule,
  ButtonModule, MenuModule, TieredMenuModule
} from 'primeng/primeng';
import { CommonTypeComponent } from './components/reference/common-type-table.component';
import { CommonTypeSelectorComponent } from './components/reference/common-type-selector.component';
import { NoopAnimationsModule} from "@angular/platform-browser/animations";
import { UserNavbarComponent } from './components/shared/navbar/user/user-navbar.component';
import { UserFooterComponent } from './components/shared/navbar/user/user-footer.component';
import { CommonTypeDroplistComponent } from './components/reference/common-type-droplist.component';
import { MockMoikaObjectService} from "./model/services/mock-moika-objects.service";
import { MockMoikaObjects} from "./model/entities/mock-moika-objects";
import { CustomerNavbarComponent} from "./components/shared/navbar/customer/customer-navbar.component";
import { WashFacilityDashboardComponent } from './components/facility/wash-facility-dashboard.component';
import {AppRoutingModule} from "./app-routing.module";
import { CommonStatusTableComponent } from './components/reference/common-status-table.component';
import { CommonStatusSelectorComponent } from './components/reference/common-status-selector.component';
import { CommonStatusDroplistComponent } from './components/reference/common-status-droplist.component';
import { WashFacilityTableComponent } from './components/facility/wash-facility-table.component';
import { CityDropdownComponent } from './components/reference/city-dropdown.component';
import { WashBoxTableComponent } from './components/facility/wash-box-table.component';
import { FacilityMapComponent } from './components/facility/facility-map.component';
import {AgmCoreModule} from "angular2-google-maps/core";


@NgModule({
  declarations: [
    AppComponent,
    WashBoxComponent,
    WashFacilityListComponent,
    ClientComponent,
    ClientListComponent,
    PhoneListComponent,
    FacilityDetailComponent,
    WashBoxDetailComponent,
    AppAboutComponent,
    NotFoundComponent,
    HomePageComponent,
    MainOutletComponent,
    CommonTypeComponent,
    CommonTypeSelectorComponent,
    UserNavbarComponent,
    UserFooterComponent,
    CommonTypeDroplistComponent,
    CustomerNavbarComponent,
    AdminNavbarComponent,
    WashFacilityDashboardComponent,
    CommonStatusTableComponent,
    CommonStatusSelectorComponent,
    CommonStatusDroplistComponent,
    WashFacilityTableComponent,
    CityDropdownComponent,
    WashBoxTableComponent,
    FacilityMapComponent,
  ],
  imports: [
    BsDropdownModule.forRoot(),
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    DataTableModule, DialogModule, SharedModule,  DataTableModule, InputTextareaModule,
    FormsModule, PanelModule, DropdownModule, ButtonModule, MenuModule, TieredMenuModule,
    NoopAnimationsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDX_0DCgqImKoRTUPQ3QRjdOwLEVKwm3uE'
    }),
  ],
  providers: [WashBoxService, WashFacilityService, ClientService, CrudService, MockMoikaObjectService, MockMoikaObjects],
  bootstrap: [AppComponent]
})
export class AppModule { }
