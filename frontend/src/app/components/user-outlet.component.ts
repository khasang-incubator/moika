import {Component} from '@angular/core';

@Component({
  selector: 'app-user-outlet',
  template: `
    <nav>
      <a routerLink="about" routerLinkActive="active"></a>
      <a routerLink="fclt" routerLinkActive="active"></a>
      <a routerLink="fclt/facilitiesDash" routerLinkActive="active"></a>
      <a routerLink="fclt/facilitiesTable" routerLinkActive="active"></a>
      <a routerLink="fclt/washFacilitiesList" routerLinkActive="active"></a>   
    </nav>
    <router-outlet></router-outlet>`
})
export class UserOutletComponent {


}
