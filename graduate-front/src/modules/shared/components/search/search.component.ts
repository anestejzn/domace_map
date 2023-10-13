import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Options } from 'ngx-google-places-autocomplete/objects/options/options';
import { Observable, map, startWith } from 'rxjs';
import { Search } from '../../model/search';
import { HouseholdService } from '../../service/household-service/household-service';
import { getImageForType } from '../../util/typeOfProduct';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
  @Output() markersEvent = new EventEmitter();
  @Output() centerEvent = new EventEmitter();
  options: Options = new Options({
    bounds: undefined,
    fields: ['address_component', 'formatted_address', 'name', 'geometry'],
    strictBounds: false,
    componentRestrictions: { country: 'rs' },
  });
  noResults = false;

  selectedProductTypes = new FormControl([]);

  productTypes = ['Jabuka', 'Šljiva', 'Jagoda', 'Malina', 'Kupina', 'Višnja', 'Breskva', 'Paprika', 'Paradajz', 'Krastavac', 'Kupus', 'Krompir', 'Pasulj', 'Šargarepa', 'Luk'];
  filteredOptions: Observable<string[]>;
  ownerControl = new FormControl('');
  markers = [];
  currentAddress = null;

  constructor(private householdService: HouseholdService) { }

  ngOnInit(): void {
    // this.filteredOptions = this.ownerControl.valueChanges.pipe(
    //   startWith(''),
    //   map(value => this.filterOwners(value || '')),
    // );
  }

  // private filterOwners(value: string): string[] {
  //   const filterValue = value.toLowerCase();

  //   return this.ownersOptions.filter(option => option.toLowerCase().includes(filterValue));
  // }

  addressChange(address: any){
    console.log(address.geometry.location.lng(), address.geometry.location.lat());
    this.noResults = false;
    this.currentAddress = address;
    const search: Search = {
      lon: address.geometry.location.lng(),
      lat: address.geometry.location.lat(),
      productType: this.selectedProductTypes.value.length > 0 ? this.selectedProductTypes.value : null
    }

    this.getResults(search);
  }

  getResults(search: Search){
    this.householdService.getOffersForSearch(search).subscribe(
      (response: Search[]) => {
        if(response.length > 0){
        for(const r of response){
          console.log(r);
          this.noResults = false;
          this.markers.push({
            position: {
              lat: r.lat,
              lng: r.lon
            },
            info: {
              household: r.household.name,
              householdId: r.household.id,
              description: r.offer.description,
              price: r.offer.price,
              colForPrice: r.offer.colForPrice,
              average: r.offer.averageRate,
              name: r.offer.name,
              id: r.offer.id
            },
            options: {icon: {url: getImageForType(r.offer.type),  scaledSize: new google.maps.Size(30, 30), }}
          })
        }
        if(this.currentAddress !== null) {
          this.centerEvent.emit({lat: this.currentAddress.geometry.location.lat(), lng: this.currentAddress.geometry.location.lng()});
        }
        else if(this.markers.length > 0){
          this.centerEvent.emit({lat: this.markers[0].position.lat, lng: this.markers[0].position.lng});
        }
      }
      else{
        this.noResults = true;
        this.markers = [];
      }
      this.markersEvent.emit(this.markers);
    }
    )
  }

  selectProductType(){
    this.noResults = false;
    if(this.currentAddress === null && this.selectedProductTypes.value.length === 0){
      this.noResults = false;
    } else{
    const search: Search = {
      
        lon: this.currentAddress !== null ? this.currentAddress.geometry.location.lng() : 0,
        lat: this.currentAddress !== null ? this.currentAddress.geometry.location.lat() : 0,
        productType: this.selectedProductTypes.value.length > 0 ? this.selectedProductTypes.value : null
      }

    this.getResults(search);
    }
  }

}
