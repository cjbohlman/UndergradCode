/****************************************************************
Program: BohlmanC_HW9_P2_p.c

Purpose: To make a gravitational space demonstration with a radial kick
with position output

History: created 10/15/2016

Usage: $cc -o BohlmanC_HW9_P2_p BohlmanC_HW9_P2_p.c -lm
       $BohlmanC_HW9_P2_p

Input: none

Output: position in x direction, position in y direction
****************************************************************/

#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include "animation.h"

#define GM 4*3.1415*3.1415

double acc(double r_mag, double r);

int main () {
  //initializing values
  int p1, p2; //for the animation
  double r0[2];
  double r_mid[2]; //inital position, x is 1 is y is 0
  double v0 = 1;
  double v_mid[2];
  double r[2]; //position vector, 0 is x and 1 is y
  r[0] = 1;
  r[1] = 0;
  double v[2]; //velocity vector
  v[0] = 0;
  double a[2];
  double a_mid[2];
  double r_mag = hypot(r[0],r[1]); //magnitude of position vector, uses hypot func
  double v_mag; //magnitude of velocity vector, uses hypot func
  double step = .001;
  double end = 5;
  
  double PE;
  double KE;
  double E_tot;
  double L_ang;

  v[1] = v0*2*3.1415;

  v_mag = hypot(v[0],v[1]);

  a[0] = acc(r_mag,r[0]);
  a[1] = acc(r_mag,r[1]);

  double i, i_half;
  int count = 0;
  double impulse;

  animation_start(-1.5,1.5,-1.5,1.5);
  set_clock_tick(step);

  p1 = add_particle(r[0],r[1]);//moving planet
  p2 = add_particle(0.0,0.0);//sun

   PE = -GM/r_mag;
   KE = .5*v_mag*v_mag;
   E_tot = PE+KE;

   printf("%f\t%f\n",r[0],r[1]);


  for(i=0;i<end;i=i+step) {
    if (i > 1-step/2 && i < 1 + step/2) {
      impulse = v[0] + .1;
      v[0] = v[0] + impulse;
    }
    i_half = i + (.5*step);
    r_mid[0] = r[0] + v[0]*step/2.;
    r_mid[1] = r[1] + v[1]*step/2.;
    r_mag = hypot(r_mid[0],r_mid[1]);
    v_mid[0] = v[0] + a[0]*step/2.;
    v_mid[1] = v[1] + a[1]*step/2.;
    a_mid[0] = acc(r_mag,r_mid[0]);
    a_mid[1] = acc(r_mag,r_mid[1]);

    r[0] += v_mid[0]*step;
    r[1] += v_mid[1]*step;
    r_mag = hypot(r[0],r[1]);
    v[0] += a_mid[0]*step;
    v[1] += a_mid[1]*step;
    v_mag = hypot(v[0],v[1]);
    a[0] = acc(r_mag,r[0]);
    a[1] = acc(r_mag,r[1]);

   move_particle_leave_track(p1,r[0],r[1]);
  
   PE = -GM/r_mag;
   KE = .5*v_mag*v_mag;
   E_tot = PE+KE;
   L_ang = r[0]*v[1] - r[1]*v[0];

   if(count%20 == 0) {
   printf("%f\t%f\n",r[0],r[1]);
     count = 0;
   }
   count += 1;
  }
  printf("\n");
  // animation_end();
}

double acc(double mag_r, double r) {
  double accel;
  double rcubed = fabs(mag_r)*fabs(mag_r)*fabs(mag_r);
  accel = -GM*r/rcubed;
  return accel;
}
