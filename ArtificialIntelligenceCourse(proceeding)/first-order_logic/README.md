## Predikaatloogika

### Faktid
east(shire,bree).  
east(bree,rivendell).  
west(highpass,rivendell).  
south(highpass,moria).  
east(moria,lothlorien).  
south(lothlorien,rohan).  
north(gondor,rohan).  
east(gondor,mordor).  

### Reeglid
east(X,Y) <=> west(Y,X).  
north(X,Y) <=> south(Y,X)  

(east(X,Y) & west(Z,Y)) => (east(X,Z) & west(Z,X)).  
(north(X,Y) & south(Z,Y)) => (north(X,Z) & south(Z,X)).

(east(X,Y) & south(Y,Z)) => (east(X,Z) & south(X,Z)).  
(west(X,Y) & north(Y,Z)) => (west(X,Z) & north(X,Z)).

###Päring

-east(gondor, mordor). => true  
-east(gondor, mordor). => true  
-west(mordor, gondor). => true  
-north(gondor, bree). => true  
-north(bree, gondor). => false  
-east(shire, mordor). => true  
-north(shire, mordor). => false
