:- dynamic ruut/3.

%---------MДNGU ALGSEIS-------------
% Valged
ruut(1,1,1).
ruut(1,3,1).
ruut(1,5,1).
ruut(1,7,1).
ruut(2,2,1).
ruut(2,4,1).
ruut(2,6,1).
ruut(2,8,1).
ruut(3,1,1).
ruut(3,3,1).
ruut(3,5,1).
ruut(3,7,1).
% Tьhjad ruudud
ruut(4,2,0).
ruut(4,4,0).
ruut(4,6,0).
ruut(4,8,0).
ruut(5,1,0).
ruut(5,3,0).
ruut(5,5,0).
ruut(5,7,0).
% Mustad
ruut(6,2,2).
ruut(6,4,2).
ruut(6,6,2).
ruut(6,8,2).
ruut(7,1,2).
ruut(7,3,2).
ruut(7,5,2).
ruut(7,7,2).
ruut(8,2,2).
ruut(8,4,2).
ruut(8,6,2).
ruut(8,8,2).


tee_kaik(X,Y,X1,Y1) :-
    X > 0, X < 9,
    Y > 0, Y < 9,
    ruut(X, Y, MyColour),
    retract(ruut(X, Y, MyColour)),
    retractall(ruut(X1, Y1, A)),
    assert(ruut(X, Y, 0)),
    assert(ruut(X1, Y1, MyColour)).
tee_kaik(_,_,_,_).


vota(X,Y,_,X1,Y1,X2,Y2) :-
    ruut(X, Y, MyColour),
    ruut(X1, Y1, EnemyColour),
    retract(ruut(X, Y, MyColour)),
    retract(ruut(X1, Y1, EnemyColour)),
    retract(ruut(X2, Y2, 0)),
    assert(ruut(X, Y, 0)),
    assert(ruut(X1, Y1, 0)),
    assert(ruut(X2, Y2, MyColour)),!.
vota(_,_,_,_,_,_,_).



status_sq(ROW,COL):-
  ( ruut(ROW,COL,COLOR),
    write(COLOR)
  );(
    write(' ')
  ).
status_row(ROW):-
  write('row # '),write(ROW), write('   '),
  status_sq(ROW,1),
  status_sq(ROW,2),
  status_sq(ROW,3),
  status_sq(ROW,4),
  status_sq(ROW,5),
  status_sq(ROW,6),
  status_sq(ROW,7),
  status_sq(ROW,8),
  nl.

status:-
  nl,
  status_row(8),
  status_row(7),
  status_row(6),
  status_row(5),
  status_row(4),
  status_row(3),
  status_row(2),
  status_row(1).


b:-
    status,
    tee_kaik(3, 1, 4, 2),
    status,
    tee_kaik(6, 4, 5, 3),
    status,
    vota(4, 2, _, 5, 3, 6, 4),
    status.
