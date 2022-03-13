:- dynamic mother/2. % mother(Child, Mother).
:- dynamic married/2. % married(Female, Male).
:- dynamic male/1. % male(Person).
:- dynamic female/1. % female(Person).

female(alika).
female(alisa).
female(liza).
female(oksana).
female(lilia).
female(vasselina).
female(nina).
female(tanja).

male(ilya).
male(dima).
male(aris).
male(sasha).
male(valentin).
male(aivo).
male(vasja).
male(fedja).

married(oksana,sasha). % married(Female, Male).
married(lilia,valentin). % married(Female, Male).
married(vasselina,aivo). % married(Female, Male).
married(nina,vasja). % married(Female, Male).
married(tanja,fedja). % married(Female, Male).

mother(alika,oksana). % mother(Child, Mother).
mother(ilya,oksana). % mother(Child, Mother).
mother(liza,vasselina). % mother(Child, Mother).
mother(aris,vasselina). % mother(Child, Mother).
mother(alisa,lilia). % mother(Child, Mother).
mother(dima,lilia). % mother(Child, Mother).
mother(oksana,tanja). % mother(Child, Mother).
mother(lilia,tanja). % mother(Child, Mother).
mother(sasha,nina). % mother(Child, Mother).
mother(vasselina,nina). % mother(Child, Mother).

:- dynamic father/2. % father(Child, Father).
father(X,Y) :- mother(X,Z) , married(Z,Y).

:- dynamic brother/2. % brother(Child, Brother).
brother(X,Y) :- mother(X,Z) , mother(Y,Z) , male(Y) , X \= Y.

:- dynamic brother/2. % sister(Child, Sister).
sister(X,Y) :- mother(X,Z) , mother(Y,Z) , female(Y) , X \= Y.

:- dynamic brother/2. % aunt(Child, Aunt).
aunt(X,Y) :- (mother(X,Z) ; father(X,Z)) , sister(Z,Y).

:- dynamic brother/2. % uncle(Child, Uncle).
uncle(X,Y) :- (mother(X,Z) ; father(X,Z)) , brother(Z,Y).

:- dynamic grandfather/2. % grandfather(Child, Grandfather).
grandfather(X,Y) :- (mother(X,Z) ; father(X,Z)) , father(Z,Y).

:- dynamic grandfather/2. % grandmother(Child, Grandmother).
grandmother(X,Y) :- (mother(X,Z) ; father(X,Z)) , mother(Z,Y).
