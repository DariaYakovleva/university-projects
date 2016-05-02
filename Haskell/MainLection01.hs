{-# LANGUAGE NoImplicitPrelude #-}
module MainLection01 where

import Prelude(undefined, Int, Show, Eq, (==))

-- Just an algebraic data type
{- data MaybeInt = Nothing
                | Just Int
                deriving (Show,Eq)
                -}

-- An Algebraic data type with type-argument.
-- Think about it like about function's.
data Maybe a = Nothing
             | Just a 

f :: Int
f a = 5 + a

-- Function transforms.
-- f 10 = f 10
-- f 10 = (5 + a) where a = 10
-- f 10 = (5 + 10)
-- f 10 = 15

-- "Data-type with arguments" transforms (not syntactically correct!!)
-- x :: Just a where a = Int
-- x :: Just Int

-- More then one argument
data VectorTheBest a b = Constructor a b
                        
data MagicPair = MagicPairOppa Int Double
f :: Int -> Point

VectorTheBest Int
VectorTheBest Bool
VectorTheBest Char

a = magicOperation (5 5)
data VTBIntInt = VTB Int Int

data Bool = True | False
data Int = -2^31 + 1 | 1 | 2 | 3 | 4 ...
data Person = Vasya | Petya | Gena
-- data Chair = EmptyChair | ChairWithPerson Person
data Chair = ChairWithPersonVasya | ChiarWithPersonPetya | ...

data Tuple Int Int = (1, 1) | (1, 2) | (1, 3) | ...
data ... = V2D 1 | V2D 2 | ... 
5, "", EmptyChai, ChairWithPersonVasya
data String = "" | "a" | "aa" | "ab" | ...

VTB 5 6 = "VTB 5 6"

f :: VectorTheBest
f = VTB 



data Vector2D a = V2D a a
-- first and second - functions used to access to "members" of V2D
first :: Vector2D a -> a
first (V2D x y) = x
second (V2D x y) = y

-- short syntax
data Vector2D a = V2D { first :: a
                        , second :: a
                      }

data Type = X | Y

not :: Bool -> Bool
not False = undefined
not x = if x == True then False else True

False ?===? True
True ?===? True
>>> not True


first :: Vector2D a -> a
first (V2D x y) = x


-- Pattern matching
(V2D x y) ?===? (V2D 1 2)
(x, y) ?===? (1, 2)
x = 1
y = 2
>>> first (V2D 1 2)

-- This can be translated in lambda's, really!
first v = case v of
            (V2D x y) -> x
            _         -> undefined
-- guard expressions
first v
            | v == (V2D x y) = x
            | True           = undefined

--factorial

factorial :: Int -> Int
factorial 1 = 1
factorial x = x * factorial (x - 1)

factorial x = case x of
                        1 -> 1
                        x -> x * factorial (x - 1)

factorial x
            | x == 1 = 1
            | otherwise = x * factorial(x - 1)

plus :: a -> a -> a
plus = undefined
plus :: a -> (a -> a)
plus :: a -> a -> a
plus :: (a -> a -> a)



-- plus = error "Something is fucking wrong"

{- plus :: a -> a -> a
plus = undefined

one :: a
one = undefined

gvplusone :: (Vector2D a) -> (Vector2D)


gvplusone (V2D a) = V2D

data Intersection a = None | One a | Two a a
-}
