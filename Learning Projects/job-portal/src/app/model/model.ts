export interface Job {
  id: number;
  jobTitle: string;
  company: CompanyDto;
  jobDescription: string;
  jobRequirement: string;
  jobResponsibilities: string;
  compensationBenefit: string;
  workPlace: string;
  employmentStatus: string;
  jobLocation: string;
  createdAt: string; // or Date
}

export interface CompanyDto {
  id: number;
  name: string;
  email: string;
  phone: string;
  business: string;
  address: string;
  createdAt: string; // or Date
}


export interface Company {
  id: number;
  name: string;
  email: string;
  role: 'COMPANY'; // match with your Role enum
  phone: string;
  business: string;
  address: string;
  createdAt: string; // or Date
}

export interface Seeker {
  id: number;
  name: string;
  email: string;
  education: string;
  jobExperience: string;
  designation: string;
  phone: string;
  address: string;
}


export interface JobApply{
    id: number;
    jobSeeker: Seeker;
    job: Job;
    createdAt: string;
}

export interface AdminProfile {
  id: number;
  name: string;
  email: string;
  password: string; // hidden in frontend
  phone: string;
  createdAt: string; // or Date
}

export interface EditAdminProfile {
  userId: number;
  name: string;
  phone: string;
}

export interface EditCompanyProfile {
  id: number;
  name: string;
  business: string;
  phone: string;
  address: string;
}