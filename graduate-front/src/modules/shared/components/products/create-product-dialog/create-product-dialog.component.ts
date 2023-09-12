import { CdkTextareaAutosize } from '@angular/cdk/text-field';
import { Component, Inject, NgZone, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Select } from '@ngxs/store';
import { ToastrService } from 'ngx-toastr';
import { Observable, ReplaySubject, Subscription, of, take } from 'rxjs';
import { Household } from 'src/modules/shared/model/household';
import { Offer } from 'src/modules/shared/model/offer';
import { ConfigService } from 'src/modules/shared/service/config-service/config.service';
import { AppState } from 'src/modules/shared/state/app.state';
import { OfferService } from 'src/modules/user/service/offer.service';

@Component({
  selector: 'app-create-product-dialog',
  templateUrl: './create-product-dialog.component.html',
  styleUrls: ['./create-product-dialog.component.scss']
})
export class CreateProductDialogComponent implements OnInit {
  base64Var: string;
  createProductSubscription: Subscription;
  @ViewChild('autosize') autosize: CdkTextareaAutosize;
  @Select(AppState.getHousehold)
  household: Observable<Household>;
  storedHousehold: Household;
  base64String: string;
  name: string;
  imagePath: string;
  imageurls = [];
  removedImages = true;

  createProductForm = new FormGroup(
    {
      name: new FormControl('', [
        Validators.required,
      ]),
      description: new FormControl(''),
      price: new FormControl('', [
        Validators.required,
      ]),
      colForPrice: new FormControl('', [
        Validators.required,
      ]),
      productType: new FormControl('', [Validators.required])
    }
  );

  triggerResize() {
    // Wait for changes to be applied, then trigger textarea resize.
    this._ngZone.onStable.pipe(take(1)).subscribe(() => this.autosize.resizeToFitContent(true));
  }

  constructor(
    @Inject(MAT_DIALOG_DATA) public product: Offer,
    private toast: ToastrService,
    private _ngZone: NgZone,
    private configService: ConfigService,
    private offerService: OfferService
  ) { }

  productTypes = ['Jabuka', 'Šljiva', 'Jagoda', 'Malina', 'Kupina', 'Višnja', 'Breskva', 'Paprika', 'Paradajz', 'Krastavac', 'Kupus', 'Krompir', 'Pasulj', 'Šargarepa', 'Luk'];
  ngOnInit(): void {
    this.household.subscribe(response => {
      this.storedHousehold = response;
    });
    if(this.product !== null){
      this.createProductForm.get('name').setValue(this.product.name);
      this.createProductForm.get('description').setValue(this.product.description);
      this.createProductForm.get('price').setValue(this.product.price.toString());
      this.createProductForm.get('colForPrice').setValue(this.product.colForPrice);
      this.createProductForm.get('productType').setValue(this.product.type);
      this.imageurls = this.product.photos;
    }

  }

  convertFile(file: File): Observable<string> {
    const result = new ReplaySubject<string>(1);
    const reader = new FileReader();
    reader.readAsBinaryString(file);
    reader.onload = event => result.next(btoa(event.target.result.toString()));
    return result;
  }

  getBase64Prefix(): string {
    return this.configService.BASE64_PHOTO_PREFIX;
  }
 
  removeImage(i) {
    this.imageurls.splice(i, 1);
    this.removedImages = false;
  }

  onSelectFile(event) {
    if (event.target.files && event.target.files[0]) {
      var filesAmount = event.target.files.length;
      for (let i = 0; i < filesAmount; i++) {
        this.convertFile(event.target.files[i]).subscribe(base64 => {
          this.imageurls.push(base64);
        });
      }
    }
  }

  isDisabledCreateButton(): boolean {

    return this.createProductForm.invalid;
  }

  isDisabledUpdateButton(): boolean {
    const name = this.createProductForm.get('name').value;
    const description = this.createProductForm.get('description').value;
    const price = this.createProductForm.get('price').value;
    const colForPrice = this.createProductForm.get('colForPrice').value;
    if(!this.removedImages){
      return false;
    }
    for(const photo of this.product.photos){
      if(!this.imageurls.includes(photo)){
        return false;
      }
    }

    return !(name !== this.product.name || description !== this.product.description 
    || +price !== this.product.price || colForPrice !== this.product.colForPrice);
  }

  updateProduct(){
    this.product.name = this.createProductForm.get('name').value;
    this.product.description = this.createProductForm.get('description').value;
    this.product.price = +this.createProductForm.get('price').value;
    this.product.colForPrice = this.createProductForm.get('colForPrice').value;
    this.product.photos = this.imageurls;
  }

  createNewProduct() {
    const offer : Offer = {
      name: this.createProductForm.get('name').value,
      description: this.createProductForm.get('description').value,
      price: +this.createProductForm.get('price').value,
      colForPrice: this.createProductForm.get('colForPrice').value,
      photos: this.imageurls,
      householdId: this.storedHousehold.id,
      type: this.createProductForm.get('productType').value
    };

    this.offerService.saveOffer(offer).subscribe(
      response => {
      this.toast.success(
        'Novi proizvod je uspešno dodat.'
      );
    },
    error => {
      this.toast.error(error.error, 'Neuspešno')
    });   
  }


  ngOnDestroy(): void {
    if (this.createProductSubscription) {
      this.createProductSubscription.unsubscribe();
    }
  }

}
