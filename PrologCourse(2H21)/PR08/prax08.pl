%======================================================
% Transitive closure
%------------------------------------------------------
% TEST1: transitive_closure(pr).
% TEST2: transitive_closure(jarjestus).
%------------------------------------------------------
:- dynamic closure/3.

transitive_closure(Relation):-
    Clause =..[Relation,X,Y],
    Clause,
    assertz(closure(1,X,Y)),fail.
transitive_closure(Predicate):-
    assert(lipp(0)),
    repeat,
    retract(lipp(_)),
    assert(lipp(0)),
    transitive_closure1(Predicate),
    lipp(0).
transitive_closure(_):-
    !, listing(closure).

transitive_closure1(Predicate):-
	closure(N,A,B),
    	closure(1,B,C),
    	not(closure(_,A,C)),     % kas juba olemas niisugune fakt?
    	N1 is N + 1,
    	assertz(closure(N1,A,C)),
	retract(lipp(_)),
	assert(lipp(1)),!.
transitive_closure1(Predicate).

% Katsehulk binaarseid predikaate
pr(s,f).
pr(d,f).
pr(f,g).
pr(r,s).

%=====================================================
% Transitive closure with metrics (minimal closure)
% TEST:
t:-
     m_transitive_closure(pr).
%=====================================================
m_transitive_closure(Relation):-
    Clause =..[Relation,D,X,Y],
    Clause,
    assertz(closure(D,X,Y)),fail.
m_transitive_closure(_):-
    closure(D1,A,B),
    closure(D2,B,C),
    D is D1 + D2,
    m_test(A,C,D),
    assertz(closure(D,A,C)), fail.
m_transitive_closure(_):-  !, listing(closure).

m_test(A,A,_):- !, fail.
m_test(A,C,_):-
    not(closure(_,A,C)),!.   % kas juba olemas niisugune fakt?
m_test(A,C,D):-
    closure(DD,A,C),        % kas leidub lьhem tee?
    D < DD,
    retract(closure(DD,A,C)).

pr(6.1,'Tartu',f).
pr(1,d,f).
pr(3,f,g).
pr(2,r,'Tartu').
pr(4,'Tartu',g).
pr(5,f,r).
%=====================================================
% Ekvivalentsi klassid
% genereerib faktid: eq_class(KLASSI NIMI, BAASHULGA NIMI(ELEMENDI NIMI, ATRIBUUT mille pohjal)).
%-----------------------------------------------------

a:-
    transitive_closure(jarjestus).
b:-
    equivalence_class(auto,5,2,0).

b:-
    listing(eq_class),!.
c:- separate.
c:- listing(eq_class).

d:- abolish(closure(_,_,_)),
    abolish(eq_class(Val,Term)).

%---------------------- Ekv. kl. leidmine baashulgal -------------------
% Hulk - baashulga nimi; Arity-elemendi atribuutide arv;
% Nr-atribuudi jrk nr, mille jдrgi ekv.kl. moodustatakse
%-----------------------------------------------------------------------


equivalence_class(Hulk,ArityNV,Nr,Distants):-
    functor(TermV, Hulk, ArityNV),  % moodustab termi nimega Hulk aarsusega ArityNV
    TermV,
    arg(Nr,TermV,Val),              % leiab termi TermV Nr-nda parameetri vддrtuse
    get_klass(Val, Klass),
    assert(eq_class(Klass,TermV)),
    functor(TermV1, Hulk, ArityNV), % moodustab termi nimega Hulk aarsusega ArityNV
    TermV1,
    arg(Nr,TermV1,Val1),            % leiab termi TermV1 Nr-nda argumendi vддrtuse
    get_klass(Val1, Klass1),
    distants(Klass,Klass1,D),           % vхrdleb, kas tegemist on ekvival. kl. kuuluva elem-ga
    D1 is abs(D),
    D1 =< Distants,
    assert(eq_class(Klass,TermV1)),
    fail.

% get_klass(2004, Klass).
get_klass(Val, Klass):-
    Vanus is 2021 - Val,
    (
    (Vanus >= 0, Vanus < 4) -> Klass = uus
    ; Vanus > 3, Vanus < 6 -> Klass = suht_uus
    ; Vanus > 5, Vanus < 9 -> Klass = kasutatud
    ; Vanus > 8, Vanus < 16 -> Klass = vana
    ; Vanus > 15, Vanus < 21 -> Klass = romu
    ; Vanus > 21 -> Klass = uunikum
    ).
%----------------------------------------------------
% Vддrtuste kauguse leidmine etteantud meetrikas
% TEST: distants(noor, rauk,V).
%-----------------------------------------------------
distants(Obj1, Obj2, Val):-
    closure(_,_,_),
    (closure(Val, Obj1, Obj2); (closure(Val1,Obj2,Obj1), Val is 0 -Val1);Val=999),!.
distants(Obj1,Obj2, Val):-
    transitive_closure(jarjestus),
    distants(Obj1,Obj2, Val),!.


%------------------------------------------------------
% Hulkade ja relatsioonide esitus
%------------------------------------------------------
%------------------------------------------------------
% Autode hulk
%----------------------------------------------------
auto(10000001, 2021, opel, must, janar).
auto(10000002, 2011, toyota, valge, rasmus).
auto(10000003, 2012, merz, must, peeter).
auto(10000004, 2014, audi, hall, jaak).
auto(10000005, 2003, opel, sinine, helen).
auto(10000006, 2009, toyota, punane, tom).
auto(10000007, 2021, audi, hall, jack).
auto(10000008, 2020, audi, must, ella).
auto(10000009, 2010, reno, valge, mari).
auto(10000010, 2018, opel, hall, karl).
auto(10000011, 1999, merz, hall, virve).
auto(10000012, 2004, opel, hall, lena).
auto(10000013, 2009, toyota, roheline, kim).
auto(10000014, 2018, merz, sinine, anna).
auto(10000015, 2017, toyota, sinine, oleg).

tyyp(number,[10000001, 10000002, 10000003, 10000004, 10000005, 10000006, 10000007, 10000008, 10000009, 10000010, 10000011, 10000012, 10000013, 10000014, 10000015]).  %
tyyp(valjalaske_aasta,[1999,2003,2004,2009,2010,2011,2012,2014,2017,2018,2020,2021]).  %
tyyp(mark,[opel, toyota, merz, audi, reno]).  %
tyyp(varvus,[must, valge, hall, sinine, punane, roheline]).  %
tyyp(omanik,[janar, rasmus, peeter, jaak, helen, tom, jack, ella, mari, karl, virve, lena, kim, anna, oleg]).  %
tyyp(auto,number,valjalaske_aasta, mark,varvus,omanik).

jarjestus(uus, suht_uus).
jarjestus(suht_uus, kasutatud).
jarjestus(kasutatud, vana).
jarjestus(vana, romu).
jarjestus(romu, uunikum).


%------------------------------------------------------
% Inimeste hulk
%----------------------------------------------------
inimene(peeter, noor).
inimene(ain, vana).
inimene(elsa, keskealine).
inimene(reet, laps).
inimene(juuli, rauk).
inimene(agnes, kyps).
inimene(tiina, keskealine).
inimene(juljus, rauk).

tyyp(vanus,[laps,noor,kyps,keskealine,vana,rauk]).  %
tyyp(nimi,[peeter, ain, elsa, rein, reet, juuli,maie, agnes, tiina, juljus, aadolf]).
tyyp(inimene,nimi,vanus).

jarjestus(laps, noor).
jarjestus(noor, kyps).
jarjestus(kyps, vana).
jarjestus(noor, keskealine).
jarjestus(keskealine, vana).
jarjestus(vana, rauk).

%===============================================================================
set_union(A,B,U):-
    append(A,B,U1),
    list_to_set(U1, U),!.

join([], A, A).
join([A|B], C, [A|D]) :-
        join(B, C, D).
%--------------------------------

set_complement(F,FC):-
    assert(state_set([])),
    transition(Source,Destination,_),
    retract(state_set(SS)),
    assert(state_set([Source,Destination|SS])),
    fail.
set_complement(F,FC):-
    retract(state_set(SS)),
    remove_dups(SS,S),
    set_difference(S,F,FC).


%-------------------------------------------------------------------
% multihulgast hulga tegemine
% TEST: multiset_set([d,g,e,r,s,d,r,s,a],Set)
% Vaata ka sьsteemsete predikaati "remove_dups(List, NoDupsListV)"
%-------------------------------------------------------------------
multiset_set([],[]).
multiset_set([El|Mset],Set):-
    multiset_set(Mset,Set1),
    ((not(l_inclusion(El,Set1)),Set=[El|Set1]);Set=Set1).


%=====================================================
% Abipredikaadid
%-----------------------------------------------------
%abs(E,A):-      % absoluutvддrtuse leidmine
%    E < 0, A is E * -1,!.
%abs(E,E):- !.

%-----------------------------------------------------
%length([], 0).      % listi pikkus leidmine
%length([S|T], M):- length(T, N), M is N + 1.

%-----------------------------------------------------
arity(CL,ArityNV):- % Predikaadi aarsuse leidmine
    (TermV=..[CL,A],call(TermV),ArityNV=1);
    (TermV=..[CL,A,B],call(TermV),ArityNV=2);
    (TermV=..[CL,A,B,C],call(TermV),ArityNV=3);
    (TermV=..[CL,A,B,C,D],call(TermV),ArityNV=4);
    (TermV=..[CL,A,B,C,D,E],call(TermV),ArityNV=5).
%-----------------------------------------------------

separate:-
    eq_class(Class, inimene(Nimi, Attr)),
    eq_class(Classx, inimene(Nimi, Attrx)),
    Class \= Classx,
    retract(eq_class(Classx, inimene(Nimi, Attrx))),
    fail.
%--------------------------------------------------------
% Hulkade vahe. Analoogne sьsteemipredikaat:
% compare_lists(List1, List2, DiffListV) - DiffList is the
% elements of List1 that are not in List2
%--------------------------------------------------------
set_difference(S,[],S).
set_difference(S,[El|F],FC):-
    remove(El,S,S1),
    set_difference(S1,F,FC).

%-------------------------------------------------------------------
% Elemendi sisalduvus listis. Analoogne sьsteemipredikaat member(El, List)
% TEST: l_inclusion(d,[f,s,e,d,a]).
%-------------------------------------------------------------------
l_inclusion(El,[]):- fail.
l_inclusion(El,[El|_]).
l_inclusion(El,[El1|List]):- !,
    l_inclusion(El,List).
%-------------------------------------------------------------------
%+++++++++++++++++++++++++++++++++++
% Elemendi eemaldamine listist
% Test: remove(ats,[reet,pets, ott, ats], Uus_list).
%-----------------------------------
%  remove(S, [S|T], L):- remove(S,T,L),!.
%  remove(S, [S|T], T).
%  remove(S, [U|T], [U|L]):- remove(S,T,L).
%---------------------------------------
% Hulkade ьhisosa
% TEST: intersection([a,s,d,f,g,h,g],[s,f,h],H).
%----------------------------------------
intersection([],L2,[]):- !.
intersection([El|L1],L2,L3):-
    intersection(L1,L2,L),
    ((is_member(El,L2),L3=[El|L]);L3=L),!.

