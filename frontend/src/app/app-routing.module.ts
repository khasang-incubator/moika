import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomePageComponent} from "./components/shared/common/home-page.component";
import {AppAboutComponent} from "./components/shared/common/app-about.component";
import {ClientListComponent} from "./components/client/client-list.component";
import {CommonTypeSelectorComponent} from "./components/reference/common-type-selector.component";
import {NotFoundComponent} from "./components/shared/common/not-found.component";
import {CommonStatusSelectorComponent} from "./components/reference/common-status-selector.component";
import {WashFacilityDashboardComponent} from "./components/facility/wash-facility-dashboard.component";
import {WashFacilityTableComponent} from "./components/facility/wash-facility-table.component";
import {WashFacilityListComponent} from "./components/facility/wash-facility-list.component";
import {fcltRoutes} from "./components/facility/facility-routes";
import {FacilityMainComponent} from "./components/facility/facility-main.component";

//  { path: 'facilitiesDash', component: WashFacilityDashboardComponent},
// { path: 'facilitiesTable', component: WashFacilityTableComponent},
// { path: 'washFacilitiesList', component: WashFacilityListComponent},


// определение маршрутов
const appRoutes: Routes =[
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  { path: '#', redirectTo: '/home', pathMatch: 'full'},
  { path: 'about', component: AppAboutComponent},
  { path: 'clients', component: ClientListComponent},
 // { path: 'fclt', component:FacilityMainComponent, children: fcltRoutes},
  { path: 'facilitiesDash', component: WashFacilityDashboardComponent},
  { path: 'facilitiesTable', component: WashFacilityTableComponent},
  { path: 'washFacilitiesList', component: WashFacilityListComponent},
  { path: 'home', component: HomePageComponent},
  { path: 'typeRefs', component: CommonTypeSelectorComponent},
  { path: 'statusRefs', component: CommonStatusSelectorComponent},
  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [ RouterModule.forRoot(appRoutes)  ],
  exports: [ RouterModule ]
})

export class AppRoutingModule {}

