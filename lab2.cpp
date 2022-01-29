#include <iostream>
#include <cmath>
#include <vector>
#include <algorithm>
using namespace std;

int greedy(vector<vector<int>> matrix) {
   bool vertexes[matrix.size()]; 

   for (int k = 0; k < matrix.size(); k++)
      vertexes[k] = false;

   int i, j;
   int min = INT_MAX;

   for (int k = 0; k < matrix.size(); k++)
      for (int z = 0; z < matrix[k].size(); z++)
         if (matrix[k][z] < min && k != z) {
            min = matrix[k][z];
            i = k;
            j = z;
         }
   
   vertexes[i] = true;
   vertexes[j] = true;
   int path = min;

   for (int k = 0; k < matrix.size() - 2; k++) {
      int x = 0;
      int y = 0;
      int min_x = INT_MAX;
      int min_y = INT_MAX;
      for (int z = 0; z < matrix[0].size(); z++) {
         if (vertexes[z])
            continue;
         if (i != z) {
            if (matrix[i][z] < min_x) {
               min_x = matrix[i][z];
               x = z;
            }
         }
         if (j != z) {
            if (matrix[j][z] < min_y)
            {
               min_y = matrix[j][z];
               y = z;
            }
         }
      }

      if (min_x <= min_y) {
         i = x;
         vertexes[i] = true;
         path += min_x;
      }
      else {
         j = y;
         vertexes[j] = true;
         path += min_y;
      }
   }
   path += matrix[i][j];
   return path;
}

int bruteForce(vector<vector<int>> graph, int s, int n) {
   // store all vertex apart from source vertex
   vector<int> vertexes;
   for (int i = 0; i < n; i++)
      if (i != s)
         vertexes.push_back(i);
 
   int min_path = INT_MAX;
   do {
      int current_pathweight = 0;
      int k = s;

      for (int i = 0; i < vertexes.size(); i++) {
         current_pathweight += graph[k][vertexes[i]];
         k = vertexes[i];
      }
      current_pathweight += graph[k][s];
      min_path = min(min_path, current_pathweight);
   } while (next_permutation(vertexes.begin(), vertexes.end()));
 
   return min_path;
}

int main(void) {
   int n, a, b;
   cout << "Unesite redom, odvojene razmakom, parametre n, a i b: ";
   cin >> n >> a >> b;
   cout << endl;

   vector<vector<int>> matrix(n, vector<int>(n));
   for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
         int value;
         if (i < j)
            value = pow((a * (i + 1) + b * (j + 1)), 2) + 1;
         else if (i > j) {
            value = pow((a * (j + 1) + b * (i + 1)), 2) + 1;
         } else 
            value = 0;
         matrix[i][j] = value;
      }
   }

   int s = 0;
   int gr = greedy(matrix);
   int bF = bruteForce(matrix, s, n);

   cout << "Pohlepni algoritam nalazi ciklus duljine " << gr << endl;
   cout << "Iscrpni algoritam nalazi ciklus duljine " << bF << endl;

   if (gr == bF)
      cout << "Pohlepni algoritam na ovom grafu daje optimalno rjesenje!";
   else 
      cout << "Pohlepni algoritam na ovom grafu ne daje optimalno rjesenje!";

   return 0;
}