export interface RegisterRequest {
  name: string;
  email: string;
  password: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface Package {
  id: number;
  packageName: string;
  packageDescription: string;
  packageImage: string;
  packagePrice: number;
}

