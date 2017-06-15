import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { NoopAnimationsModule} from "@angular/platform-browser/animations";
import {AppRoutingModule} from "./app-routing.module";

import {
  DataTableModule, DropdownModule, InputTextareaModule, PanelModule, DialogModule, SharedModule,
  ButtonModule, MenuModule, TieredMenuModule, FieldsetModule, CarouselModule, PasswordModule, AccordionModule,
  PanelMenuModule
} from 'primeng/primeng';

import { BsDropdownModule} from "ngx-bootstrap";
import {AgmCoreModule} from "angular2-google-maps/core";

import { AppComponent } from './app.component';
import { WashBoxComponent} from './components/facility/wash-box-list.component';
import { WashBoxService} from './model/services/wash-box.service';
import { WashFacilityListComponent } from './components/facility/wash-facility-list.component';
import { WashFacilityService} from './model/services/wash-facility.service';
import { ClientService} from "./model/services/client.service";
import { ClientComponent } from './components/client/client.component';
import { ClientListComponent } from './components/client/client-list.component';
import { CrudService} from "./model/services/crud.service";
import { PhoneListComponent } from './components/client/phone-list.component';
import { FacilityDetailComponent } from './components/facility/facility-detail.component';
import { WashBoxDetailComponent } from './components/facility/wash-box-detail.component';
import { AppAboutComponent } from './components/shared/common/app-about.component';
import { NotFoundComponent } from './components/shared/common/not-found.component';
import { HomePageComponent } from './components/shared/common/home-page.component';
import { MainOutletComponent } from './components/main-outlet.component';
import { CommonTypeComponent } from './components/reference/common-type-table.component';
import { CommonTypeSelectorComponent } from './components/reference/common-type-selector.component';
import { CommonTypeDroplistComponent } from './components/reference/common-type-droplist.component';
import { MockMoikaObjectService} from "./model/services/mock-moika-objects.service";
import { MockMoikaObjects} from "./model/entities/mock-moika-objects";
import { WashFacilityDashboardComponent } from './components/facility/wash-facility-dashboard.component';
import { CommonStatusTableComponent } from './components/reference/common-status-table.component';
import { CommonStatusSelectorComponent } from './components/reference/common-status-selector.component';
import { CommonStatusDroplistComponent } from './components/reference/common-status-droplist.component';
import { WashFacilityTableComponent } from './components/facility/wash-facility-table.component';
import { CityDropdownComponent } from './components/reference/city-dropdown.component';
import { WashBoxTableComponent } from './components/facility/wash-box-table.component';
import { FacilityMapComponent } from './components/facility/facility-map.component';
import { LoginDialogComponent } from './components/shared/common/login-dialog.component';
import {FooterComponent} from "./components/shared/footer/footer.component";
import {CommonNavbarComponent} from "app/components/shared/navbar/common-navbar.component";
import { LandingComponent } from './components/shared/landing.component';
import {InterfaceSwitchingService} from "./model/services/interface-switching.service";
import {MenuToggleService} from "./model/services/menu-toggle.service";
import {MainMenuComponent} from "./components/shared/menu/main-menu.component";
import {UserOutletComponent} from "./components/user-outlet.component";
import {AdminOutletComponent} from "./components/admin-outlet.component";
import {FcltOwnerOutletComponent} from "./components/fclt-owner-outlet.component";
import {AutoOwnerOutletComponent} from "app/components/auto-owner-outlet.component";


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
    CommonTypeDroplistComponent,
    WashFacilityDashboardComponent,
    CommonStatusTableComponent,
    CommonStatusSelectorComponent,
    CommonStatusDroplistComponent,
    WashFacilityTableComponent,
    CityDropdownComponent,
    WashBoxTableComponent,
    FacilityMapComponent,
    LoginDialogComponent,
    FooterComponent,
    CommonNavbarComponent,
    LandingComponent,
    MainMenuComponent,
    UserOutletComponent,
    AdminOutletComponent,
    FcltOwnerOutletComponent,
    AutoOwnerOutletComponent,
  ],
  imports: [
    BsDropdownModule.forRoot(),
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    DataTableModule, DialogModule, SharedModule,  DataTableModule, InputTextareaModule,
    FormsModule, PanelModule, DropdownModule, ButtonModule, MenuModule, TieredMenuModule,
    NoopAnimationsModule, FieldsetModule, CarouselModule, PasswordModule, AccordionModule,
    PanelMenuModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDX_0DCgqImKoRTUPQ3QRjdOwLEVKwm3uE'
    }),
  ],
  providers: [WashBoxService, WashFacilityService, ClientService, CrudService, MockMoikaObjectService, MockMoikaObjects,
    InterfaceSwitchingService, MenuToggleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
