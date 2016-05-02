{-# LANGUAGE FlexibleContexts #-} 
module Test where
foo x = x + 2
fff f g = map (f . g)
data Tree a = Leaf | Node a (Tree a) (Tree a) deriving Show

insert :: Ord a => a -> Tree a -> Tree a
insert x Leaf = Node x (Leaf) (Leaf)
insert x (Node key left right) = if (x < key) then Node key (insert x left) right else Node key left (insert x right) 

merge :: Tree a -> Tree a -> Tree a
merge Leaf t = t
merge t Leaf = t
merge (Node key1 l1 r1) t = Node key1 (merge l1 r1) t  


delete :: Ord a => a -> Tree a -> Tree a
delete x Leaf = Leaf
delete x (Node key left right)  
				| x == key = merge left right 
				| x > key = Node key left (delete x right)
				| x < key = Node key (delete x left) right    

data Vector = Vector Float Float
mulVector :: Vector -> Float -> Vector
mulVector (Vector x1 y1) a = Vector (x1*a) (y1*a) 

class Monoid a where
	muli :: a -> a -> a
	muliV :: a -> Float -> a
	one :: a
instance Monoid Float where
	muli = (*)
	one = 1
instance Monoid Vector where
	muliV = mulVector
	one = Vector 1 1

class Monoid a => Group a where
	inv :: a -> a

ff :: Float -> Float
ff x = 1 / x
ffV :: Vector -> Vector
ffV (Vector x y) = Vector (ff x) (ff y) 
instance Group Float where
	inv = ff 
 
instance Group Vector where
	inv = ffV
class Monoid a => Ring a where
	sumV :: a -> a -> a
	inv' :: a -> a
	nul :: a

ff' :: Float -> Float
ff' a = -a
sumVector :: Vector -> Vector -> Vector
sumVector (Vector x1 y1) (Vector x2 y2) = Vector (x1 + x2) (y1 + y2)

ffV' :: Vector -> Vector
ffV' (Vector x y) = Vector (ff' x) (ff' y)

instance Ring Float where
	sumV = (+)
	inv' = ff'
	nul = 0
instance Ring Vector where
	sumV = sumVector
	inv' = ffV'
	nul = Vector 0 0
class Ring a => Field a where
	inv'' :: a -> a

instance Field Float where
	inv'' = ff

instance Field Vector where
	inv'' = ffV
v :: Vector -> Float -> Vector
v vec t = (muliV) vec (inv'' t)

a :: Vector -> Float -> Vector
a vec t = v (v vec t) t 
 
s :: Vector -> Vector -> Vector -> Float -> Vector
s s0 v a t = (sumV) s0 ((sumV) ((muliV) v t) ((muliV) ((muliV) ((muliV) a t) t) (inv'' 2.0))
