export interface AppointmentRequest {
  patientId: number;
  doctorId: number;
    date: string;
  time: string;
}

export interface DoctorRegisterRequestDto {
  name: string;
  email: string;
  password: string;
  specialization: string;
  qualification: string;
  experience: number;
  hospitalName: string;
  phone: string;
}

export interface DoctorResponseDto {
  id: number;
  name: string;
  specialization: string;
  qualification: string;
  experience: number;
  hospitalName: string;
  phone: string;
  image: string; // image URL or base64
}

export interface DoctorListDto {
  id: number;
  name: string;
  specialization: string;
  qualification: string;
  experience: number;
  hospitalName: string;
  phone: string;
  image: string
}


