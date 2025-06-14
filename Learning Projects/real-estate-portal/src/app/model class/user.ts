export interface UserRegistration{
    name: string;
    email: string;
    password: string;
    phone: string;
}

export interface GetPostedProperty{

    id: number;
    type: string;
    title: string;
    propertyBedroom: number;
    propertyBathroom: number;
    propertySize: number;
    propertyGarage: number;
    propertyYearBuilt: number;
    description: string;
    isAvailable: boolean;
    propertyPrice: number;
    datePosted: Date;
    address: string;
    imageUrls: string[];
}

export interface PropertyInfoResponseDto {
  propertyPostId: number;
  type: string; // assuming 'Type' is an enum or string from backend
  title: string;
  propertyBedroom: number;
  propertyBathroom: number;
  propertySize: number;
  propertyGarage: number;
  propertyYearBuilt: number;
  description: string;
  isAvailable: boolean;
  propertyPrice: number;
  datePosted: string; // use `Date` if you convert it using `new Date(...)`
  address: string;
  imageUrls: string[];
}