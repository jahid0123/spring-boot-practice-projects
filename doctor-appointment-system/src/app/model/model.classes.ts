export interface Appointment {
  id: number;
  date: string;
  status: 'PENDING' | 'APPROVED' | 'REJECTED';
  patientName: string;
  doctorId: number;
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
  name: string;
  specialization: string;
  qualification: string;
  experience: number;
  hospitalName: string;
  phone: string;
  image: string; // image URL or base64
}

