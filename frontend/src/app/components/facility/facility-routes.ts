import {RouterModule, Routes} from "@angular/router";
import {WashFacilityDashboardComponent} from "./wash-facility-dashboard.component";
import {WashFacilityTableComponent} from "./wash-facility-table.component";
import {WashFacilityListComponent} from "./wash-facility-list.component";
import {FacilityDetailComponent} from "./facility-detail.component";
import {FacilityMainComponent} from "./facility-main.component";

export const fcltRoutes: Routes = [
      { path: '', component: FacilityMainComponent },
      { path: ':id', component: FacilityDetailComponent },
      { path: 'facilitiesDash', component: WashFacilityDashboardComponent},
      { path: 'facilitiesTable', component: WashFacilityTableComponent},
      { path: 'washFacilitiesList', component: WashFacilityListComponent},
];

//export const FcltRoutes = RouterModule.forChild(fcltRoutes);
