import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomePageComponent} from "./components/shared/common/home-page.component";
import {AppAboutComponent} from "./components/shared/common/app-about.component";
import {ClientListComponent} from "./components/client/client-list.component";
import {WashFacilityDashboardComponent} from "./components/facility/wash-facility-dashboard.component";
import {WashFacilityListComponent} from "./components/facility/wash-facility-list.component";
import {CommonTypeSelectorComponent} from "./components/reference/common-type-selector.component";
import {NotFoundComponent} from "./components/shared/common/not-found.component";
import {CommonStatusSelectorComponent} from "./components/reference/common-status-selector.component";
import {WashFacilityTableComponent} from "./components/facility/wash-facility-table.component";

// определение маршрутов
const appRoutes: Routes =[
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  { path: '#', redirectTo: '/home', pathMatch: 'full'},
  { path: 'about', component: AppAboutComponent},
  { path: 'clients', component: ClientListComponent},
  { path: 'home', component: HomePageComponent},
  { path: 'facilitiesDash', component: WashFacilityDashboardComponent},
  { path: 'facilitiesTable', component: WashFacilityTableComponent},
  { path: 'washFacilitiesList', component: WashFacilityListComponent},
  { path: 'typeRefs', component: CommonTypeSelectorComponent},
  { path: 'statusRefs', component: CommonStatusSelectorComponent},
  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [ RouterModule.forRoot(appRoutes) ],
  exports: [ RouterModule ]
})

export class AppRoutingModule {}

