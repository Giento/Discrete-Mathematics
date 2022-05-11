#include <iostream>
#include <cmath>
using namespace std;

double lambda1, lambda2;
double a_0, a_1;
int n;

double recursive(int n) {
   if (n == 0) 
      return a_0;
   else if (n == 1)
      return a_1;
   else
      return lambda1 * recursive(n - 1) + lambda2 * recursive(n - 2);
}

double formula(int n) {
   //x^2 - λ_1*x - λ_2 = 0
   //a = 1, b = -λ_1, c = -λ_2
   //D = b^2-4*a*c
   double diskriminanta = pow(-lambda1, 2) - (4 * 1 * (-lambda2));
   double x1, x2;
   x1 = (lambda1 + sqrt(diskriminanta)) / 2;
   x2 = (lambda1 - sqrt(diskriminanta)) / 2;

   double a_n, alfa, beta;
   if (abs(x1 - x2) < 1e-8) {
      alfa = a_0;
      beta = (a_1 - alfa * x1) / x1;
      a_n = alfa * pow(x1, n) + beta * n * pow(x1, n);
   } else {
      beta = (a_1 - a_0 * x1) / (x2 - x1);
      alfa = a_0 - beta;
      a_n = alfa * pow(x1, n) + beta * pow(x2, n);
   }
   return a_n;
}

int main(void) {
   
   cout << "Unesite prvi koeficijent λ_1 rekurzivne relacije: ";
   cin >> lambda1;
   cout << "Unesite drugi koeficijent λ_2 rekurzivne relacije: ";
   cin >> lambda2;

   cout << "Unesite vrijednost nultog clana niza a_0: ";
   cin >> a_0;
   cout << "Unesite vrijednost prvog clana niza a_1: ";
   cin >> a_1;

   cout << "Unesite redni broj n trazenog clana niza: ";
   cin >> n;
   cout << endl;

   cout << "Vrijednost n-tog clana niza pomocu formule: " << formula(n);
   cout << endl;
   cout << "Vrijednost n-tog clana niza iz rekurzije: " << recursive(n);

   return 0;
}