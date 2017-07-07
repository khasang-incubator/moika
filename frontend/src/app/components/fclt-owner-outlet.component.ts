import {Component} from '@angular/core';

@Component({
  selector: 'app-fclt-owner-outlet',
  template: `
    <nav>
      <a routerLink="clients" routerLinkActive="active"></a>
      <a routerLink="about" routerLinkActive="active"></a>
      <a routerLink="fclt/facilitiesDash" routerLinkActive="active"></a>
      <a routerLink="fclt/facilitiesTable" routerLinkActive="active"></a>
      <a routerLink="fclt/washFacilitiesList" routerLinkActive="active"></a>
    </nav>
    <router-outlet></router-outlet>`
})
export class FcltOwnerOutletComponent {


}
