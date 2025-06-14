import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AddService {
  private postPropertyURL = 'http://localhost:8081/api/seller/post/property';
  private updatePropertyURL = 'http://localhost:8081/api/seller/posted/property/update';

  constructor(private http: HttpClient) {}

  /**
   * Post a new property with images
   */
  postPropertyWithImages(propertyData: any, images: File[]): Observable<any> {
    const formData = new FormData();

    const propertyBlob = new Blob([JSON.stringify(propertyData)], {
      type: 'application/json',
    });

    formData.append('property', propertyBlob);

    images.forEach((file) => {
      formData.append('images', file);
    });

    return this.http.post<any>(this.postPropertyURL, formData);
  }

  /**
   * Update existing property with images
   */
  updatePropertyWithImages(propertyData: any, images: File[]): Observable<any> {
    const formData = new FormData();

    const propertyBlob = new Blob([JSON.stringify(propertyData)], {
      type: 'application/json',
    });

    formData.append('property', propertyBlob);

    images.forEach((file) => {
      formData.append('images', file);
    });

    return this.http.put<any>(this.updatePropertyURL, formData);
  }
}
