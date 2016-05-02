{-# LANGUAGE UnicodeSyntax, OverloadedStrings, NoImplicitPrelude #-}
module Base
  ( error, undefined, hole, Hole(..)
  , Bool(..), not, lor, land
  , fromBoolToString, fromStringToBool
  , Ordering(..)
  , fromOrderingToString, fromStringToOrdering
  , Char, String
  , Word, Word8, Word16, Word32, Word64
  , Int, Int8, Int16, Int32, Int64
  , Integer
  , Float, Double
  , eqBool, neBool
  , eqOrdering, neOrdering
  , ltOrdering, leOrdering, gtOrdering, geOrdering
  , compareOrdering
  , eqChar, neChar
  , ltChar, leChar, gtChar, geChar
  , compareChar
  , sumWord, subWord, mulWord
  , fromWordToString, fromStringToWord
  , eqWord, neWord
  , ltWord, leWord, gtWord, geWord
  , compareWord
  , divWord, modWord
  , sumWord8, subWord8, mulWord8
  , fromWord8ToString, fromStringToWord8
  , eqWord8, neWord8
  , ltWord8, leWord8, gtWord8, geWord8
  , compareWord8
  , divWord8, modWord8
  , sumWord16, subWord16, mulWord16
  , fromWord16ToString, fromStringToWord16
  , eqWord16, neWord16
  , ltWord16, leWord16, gtWord16, geWord16
  , compareWord16
  , divWord16, modWord16
  , sumWord32, subWord32, mulWord32
  , fromWord32ToString, fromStringToWord32
  , eqWord32, neWord32
  , ltWord32, leWord32, gtWord32, geWord32
  , compareWord32
  , divWord32, modWord32
  , sumWord64, subWord64, mulWord64
  , fromWord64ToString, fromStringToWord64
  , eqWord64, neWord64
  , ltWord64, leWord64, gtWord64, geWord64
  , compareWord64
  , divWord64, modWord64
  , sumInt, subInt, mulInt
  , fromIntToString, fromStringToInt
  , eqInt, neInt
  , ltInt, leInt, gtInt, geInt
  , compareInt
  , divInt, modInt
  , negInt
  , sumInt8, subInt8, mulInt8
  , fromInt8ToString, fromStringToInt8
  , eqInt8, neInt8
  , ltInt8, leInt8, gtInt8, geInt8
  , compareInt8
  , divInt8, modInt8
  , negInt8
  , sumInt16, subInt16, mulInt16
  , fromInt16ToString, fromStringToInt16
  , eqInt16, neInt16
  , ltInt16, leInt16, gtInt16, geInt16
  , compareInt16
  , divInt16, modInt16
  , negInt16
  , sumInt32, subInt32, mulInt32
  , fromInt32ToString, fromStringToInt32
  , eqInt32, neInt32
  , ltInt32, leInt32, gtInt32, geInt32
  , compareInt32
  , divInt32, modInt32
  , negInt32
  , sumInt64, subInt64, mulInt64
  , fromInt64ToString, fromStringToInt64
  , eqInt64, neInt64
  , ltInt64, leInt64, gtInt64, geInt64
  , compareInt64
  , divInt64, modInt64
  , negInt64
  , sumInteger, subInteger, mulInteger
  , fromIntegerToString, fromStringToInteger
  , eqInteger, neInteger
  , ltInteger, leInteger, gtInteger, geInteger
  , compareInteger
  , divInteger, modInteger
  , negInteger
  , sumFloat, subFloat, mulFloat
  , fromFloatToString, fromStringToFloat
  , eqFloat, neFloat
  , ltFloat, leFloat, gtFloat, geFloat
  , compareFloat
  , divFloat, negFloat, recipFloat
  , expFloat, logeFloat, sinFloat, asinFloat, cosFloat, acosFloat, tanFloat, atanFloat, sqrtFloat
  , logBaseFloat, atan2Float
  , sumDouble, subDouble, mulDouble
  , fromDoubleToString, fromStringToDouble
  , eqDouble, neDouble
  , ltDouble, leDouble, gtDouble, geDouble
  , compareDouble
  , divDouble, negDouble, recipDouble
  , expDouble, logeDouble, sinDouble, asinDouble, cosDouble, acosDouble, tanDouble, atanDouble, sqrtDouble
  , logBaseDouble, atan2Double
  , fromFloat2Double, fromDouble2Float
  ) where
{- Ignore all the definitions here.
   Look only at the names and the types. -}

import qualified Prelude as P
import qualified Data.Int as I
import qualified Data.Word as W
import qualified GHC.Float as F

import Data.String (IsString(..))
import Prelude ( Bool(..), Ordering(..)
               , Char
               , Integer
               , Float, Double
               )

import Data.Word (Word, Word8, Word16, Word32, Word64)
import Data.Int (Int, Int8, Int16, Int32, Int64)

{- definitions in Prelude:
data Bool = True | False

if True  then a else b = a
if False then a else b = b

data Ordering = LT | EQ | GT
-}

newtype String = String { unString :: [Char] }

{- Start magic -}
instance P.Show String where
  show = unString

instance IsString String where
  fromString = String

showString :: P.Show a => a -> String
showString x = String (P.show x)

readString :: P.Read a => String -> a
readString x = P.read (unString x)
{- END Magic -}

error :: String → a
error x = P.error (unString x)

undefined = error "undefined"
hole = undefined

data Hole = Hole

fromBoolToString :: Bool → String
fromBoolToString = showString

fromStringToBool :: String → Bool
fromStringToBool = readString

not :: Bool → Bool
not = (P.not)

lor :: Bool → Bool → Bool
lor = (P.||)

land :: Bool → Bool → Bool
land = (P.&&)

fromOrderingToString :: Ordering → String
fromOrderingToString = showString

fromStringToOrdering :: String → Ordering
fromStringToOrdering = readString

eqBool, neBool :: Bool → Bool → Bool
eqBool = (P.==)
neBool = (P./=)

eqOrdering, neOrdering :: Ordering → Ordering → Bool
eqOrdering = (P.==)
neOrdering = (P./=)

ltOrdering, leOrdering, gtOrdering, geOrdering :: Ordering → Ordering → Bool
ltOrdering = (P.<)
leOrdering = (P.<=)
gtOrdering = (P.>)
geOrdering = (P.>=)

compareOrdering :: Ordering → Ordering → Ordering
compareOrdering = (P.compare)

eqChar, neChar :: Char → Char → Bool
eqChar = (P.==)
neChar = (P./=)

ltChar, leChar, gtChar, geChar :: Char → Char → Bool
ltChar = (P.<)
leChar = (P.<=)
gtChar = (P.>)
geChar = (P.>=)

compareChar :: Char → Char → Ordering
compareChar = (P.compare)

sumWord, subWord, mulWord :: Word → Word → Word
sumWord = (P.+)
subWord = (P.-)
mulWord = (P.*)

fromWordToString :: Word → String
fromWordToString = showString

fromStringToWord :: String → Word
fromStringToWord = readString

eqWord, neWord :: Word → Word → Bool
eqWord = (P.==)
neWord = (P./=)

ltWord, leWord, gtWord, geWord :: Word → Word → Bool
ltWord = (P.<)
leWord = (P.<=)
gtWord = (P.>)
geWord = (P.>=)

compareWord :: Word → Word → Ordering
compareWord = (P.compare)

divWord, modWord :: Word → Word → Word
divWord = P.div
modWord = P.mod

sumWord8, subWord8, mulWord8 :: Word8 → Word8 → Word8
sumWord8 = (P.+)
subWord8 = (P.-)
mulWord8 = (P.*)

fromWord8ToString :: Word8 → String
fromWord8ToString = showString

fromStringToWord8 :: String → Word8
fromStringToWord8 = readString

eqWord8, neWord8 :: Word8 → Word8 → Bool
eqWord8 = (P.==)
neWord8 = (P./=)

ltWord8, leWord8, gtWord8, geWord8 :: Word8 → Word8 → Bool
ltWord8 = (P.<)
leWord8 = (P.<=)
gtWord8 = (P.>)
geWord8 = (P.>=)

compareWord8 :: Word8 → Word8 → Ordering
compareWord8 = (P.compare)

divWord8, modWord8 :: Word8 → Word8 → Word8
divWord8 = P.div
modWord8 = P.mod

sumWord16, subWord16, mulWord16 :: Word16 → Word16 → Word16
sumWord16 = (P.+)
subWord16 = (P.-)
mulWord16 = (P.*)

fromWord16ToString :: Word16 → String
fromWord16ToString = showString

fromStringToWord16 :: String → Word16
fromStringToWord16 = readString

eqWord16, neWord16 :: Word16 → Word16 → Bool
eqWord16 = (P.==)
neWord16 = (P./=)

ltWord16, leWord16, gtWord16, geWord16 :: Word16 → Word16 → Bool
ltWord16 = (P.<)
leWord16 = (P.<=)
gtWord16 = (P.>)
geWord16 = (P.>=)

compareWord16 :: Word16 → Word16 → Ordering
compareWord16 = (P.compare)

divWord16, modWord16 :: Word16 → Word16 → Word16
divWord16 = P.div
modWord16 = P.mod

sumWord32, subWord32, mulWord32 :: Word32 → Word32 → Word32
sumWord32 = (P.+)
subWord32 = (P.-)
mulWord32 = (P.*)

fromWord32ToString :: Word32 → String
fromWord32ToString = showString

fromStringToWord32 :: String → Word32
fromStringToWord32 = readString

eqWord32, neWord32 :: Word32 → Word32 → Bool
eqWord32 = (P.==)
neWord32 = (P./=)

ltWord32, leWord32, gtWord32, geWord32 :: Word32 → Word32 → Bool
ltWord32 = (P.<)
leWord32 = (P.<=)
gtWord32 = (P.>)
geWord32 = (P.>=)

compareWord32 :: Word32 → Word32 → Ordering
compareWord32 = (P.compare)

divWord32, modWord32 :: Word32 → Word32 → Word32
divWord32 = P.div
modWord32 = P.mod

sumWord64, subWord64, mulWord64 :: Word64 → Word64 → Word64
sumWord64 = (P.+)
subWord64 = (P.-)
mulWord64 = (P.*)

fromWord64ToString :: Word64 → String
fromWord64ToString = showString

fromStringToWord64 :: String → Word64
fromStringToWord64 = readString

eqWord64, neWord64 :: Word64 → Word64 → Bool
eqWord64 = (P.==)
neWord64 = (P./=)

ltWord64, leWord64, gtWord64, geWord64 :: Word64 → Word64 → Bool
ltWord64 = (P.<)
leWord64 = (P.<=)
gtWord64 = (P.>)
geWord64 = (P.>=)

compareWord64 :: Word64 → Word64 → Ordering
compareWord64 = (P.compare)

divWord64, modWord64 :: Word64 → Word64 → Word64
divWord64 = P.div
modWord64 = P.mod

sumInt, subInt, mulInt :: Int → Int → Int
sumInt = (P.+)
subInt = (P.-)
mulInt = (P.*)

fromIntToString :: Int → String
fromIntToString = showString

fromStringToInt :: String → Int
fromStringToInt = readString

eqInt, neInt :: Int → Int → Bool
eqInt = (P.==)
neInt = (P./=)

ltInt, leInt, gtInt, geInt :: Int → Int → Bool
ltInt = (P.<)
leInt = (P.<=)
gtInt = (P.>)
geInt = (P.>=)

compareInt :: Int → Int → Ordering
compareInt = (P.compare)

divInt, modInt :: Int → Int → Int
divInt = P.div
modInt = P.mod

negInt :: Int → Int
negInt = P.negate

sumInt8, subInt8, mulInt8 :: Int8 → Int8 → Int8
sumInt8 = (P.+)
subInt8 = (P.-)
mulInt8 = (P.*)

fromInt8ToString :: Int8 → String
fromInt8ToString = showString

fromStringToInt8 :: String → Int8
fromStringToInt8 = readString

eqInt8, neInt8 :: Int8 → Int8 → Bool
eqInt8 = (P.==)
neInt8 = (P./=)

ltInt8, leInt8, gtInt8, geInt8 :: Int8 → Int8 → Bool
ltInt8 = (P.<)
leInt8 = (P.<=)
gtInt8 = (P.>)
geInt8 = (P.>=)

compareInt8 :: Int8 → Int8 → Ordering
compareInt8 = (P.compare)

divInt8, modInt8 :: Int8 → Int8 → Int8
divInt8 = P.div
modInt8 = P.mod

negInt8 :: Int8 → Int8
negInt8 = P.negate

sumInt16, subInt16, mulInt16 :: Int16 → Int16 → Int16
sumInt16 = (P.+)
subInt16 = (P.-)
mulInt16 = (P.*)

fromInt16ToString :: Int16 → String
fromInt16ToString = showString

fromStringToInt16 :: String → Int16
fromStringToInt16 = readString

eqInt16, neInt16 :: Int16 → Int16 → Bool
eqInt16 = (P.==)
neInt16 = (P./=)

ltInt16, leInt16, gtInt16, geInt16 :: Int16 → Int16 → Bool
ltInt16 = (P.<)
leInt16 = (P.<=)
gtInt16 = (P.>)
geInt16 = (P.>=)

compareInt16 :: Int16 → Int16 → Ordering
compareInt16 = (P.compare)

divInt16, modInt16 :: Int16 → Int16 → Int16
divInt16 = P.div
modInt16 = P.mod

negInt16 :: Int16 → Int16
negInt16 = P.negate

sumInt32, subInt32, mulInt32 :: Int32 → Int32 → Int32
sumInt32 = (P.+)
subInt32 = (P.-)
mulInt32 = (P.*)

fromInt32ToString :: Int32 → String
fromInt32ToString = showString

fromStringToInt32 :: String → Int32
fromStringToInt32 = readString

eqInt32, neInt32 :: Int32 → Int32 → Bool
eqInt32 = (P.==)
neInt32 = (P./=)

ltInt32, leInt32, gtInt32, geInt32 :: Int32 → Int32 → Bool
ltInt32 = (P.<)
leInt32 = (P.<=)
gtInt32 = (P.>)
geInt32 = (P.>=)

compareInt32 :: Int32 → Int32 → Ordering
compareInt32 = (P.compare)

divInt32, modInt32 :: Int32 → Int32 → Int32
divInt32 = P.div
modInt32 = P.mod

negInt32 :: Int32 → Int32
negInt32 = P.negate

sumInt64, subInt64, mulInt64 :: Int64 → Int64 → Int64
sumInt64 = (P.+)
subInt64 = (P.-)
mulInt64 = (P.*)

fromInt64ToString :: Int64 → String
fromInt64ToString = showString

fromStringToInt64 :: String → Int64
fromStringToInt64 = readString

eqInt64, neInt64 :: Int64 → Int64 → Bool
eqInt64 = (P.==)
neInt64 = (P./=)

ltInt64, leInt64, gtInt64, geInt64 :: Int64 → Int64 → Bool
ltInt64 = (P.<)
leInt64 = (P.<=)
gtInt64 = (P.>)
geInt64 = (P.>=)

compareInt64 :: Int64 → Int64 → Ordering
compareInt64 = (P.compare)

divInt64, modInt64 :: Int64 → Int64 → Int64
divInt64 = P.div
modInt64 = P.mod

negInt64 :: Int64 → Int64
negInt64 = P.negate

sumInteger, subInteger, mulInteger :: Integer → Integer → Integer
sumInteger = (P.+)
subInteger = (P.-)
mulInteger = (P.*)

fromIntegerToString :: Integer → String
fromIntegerToString = showString

fromStringToInteger :: String → Integer
fromStringToInteger = readString

eqInteger, neInteger :: Integer → Integer → Bool
eqInteger = (P.==)
neInteger = (P./=)

ltInteger, leInteger, gtInteger, geInteger :: Integer → Integer → Bool
ltInteger = (P.<)
leInteger = (P.<=)
gtInteger = (P.>)
geInteger = (P.>=)

compareInteger :: Integer → Integer → Ordering
compareInteger = (P.compare)

divInteger, modInteger :: Integer → Integer → Integer
divInteger = P.div
modInteger = P.mod

negInteger :: Integer → Integer
negInteger = P.negate

sumFloat, subFloat, mulFloat :: Float → Float → Float
sumFloat = (P.+)
subFloat = (P.-)
mulFloat = (P.*)

fromFloatToString :: Float → String
fromFloatToString = showString

fromStringToFloat :: String → Float
fromStringToFloat = readString

eqFloat, neFloat :: Float → Float → Bool
eqFloat = (P.==)
neFloat = (P./=)

ltFloat, leFloat, gtFloat, geFloat :: Float → Float → Bool
ltFloat = (P.<)
leFloat = (P.<=)
gtFloat = (P.>)
geFloat = (P.>=)

compareFloat :: Float → Float → Ordering
compareFloat = (P.compare)

divFloat :: Float → Float → Float
divFloat = (P./)

-- negFloat x = -x
negFloat :: Float → Float
negFloat = P.negate

-- recipFloat x = 1 / x
recipFloat :: Float → Float
recipFloat = P.recip


piFloat :: Float
piFloat = P.pi

expFloat, logeFloat, sinFloat, asinFloat, cosFloat, acosFloat, tanFloat, atanFloat, sqrtFloat :: Float → Float
expFloat  = P.exp
logeFloat = P.log
sinFloat  = P.sin
asinFloat = P.asin
cosFloat  = P.cos
acosFloat = P.acos
tanFloat  = P.tan
atanFloat = P.atan
sqrtFloat = P.sqrt

logBaseFloat, atan2Float :: Float → Float → Float
logBaseFloat = P.logBase
atan2Float = P.atan2

sumDouble, subDouble, mulDouble :: Double → Double → Double
sumDouble = (P.+)
subDouble = (P.-)
mulDouble = (P.*)

fromDoubleToString :: Double → String
fromDoubleToString = showString

fromStringToDouble :: String → Double
fromStringToDouble = readString

eqDouble, neDouble :: Double → Double → Bool
eqDouble = (P.==)
neDouble = (P./=)

ltDouble, leDouble, gtDouble, geDouble :: Double → Double → Bool
ltDouble = (P.<)
leDouble = (P.<=)
gtDouble = (P.>)
geDouble = (P.>=)

compareDouble :: Double → Double → Ordering
compareDouble = (P.compare)

divDouble :: Double → Double → Double
divDouble = (P./)

-- negDouble x = -x
negDouble :: Double → Double
negDouble = P.negate

-- recipDouble x = 1 / x
recipDouble :: Double → Double
recipDouble = P.recip


piDouble :: Double
piDouble = P.pi

expDouble, logeDouble, sinDouble, asinDouble, cosDouble, acosDouble, tanDouble, atanDouble, sqrtDouble :: Double → Double
expDouble  = P.exp
logeDouble = P.log
sinDouble  = P.sin
asinDouble = P.asin
cosDouble  = P.cos
acosDouble = P.acos
tanDouble  = P.tan
atanDouble = P.atan
sqrtDouble = P.sqrt

logBaseDouble, atan2Double :: Double → Double → Double
logBaseDouble = P.logBase
atan2Double = P.atan2

fromFloat2Double :: Float → Double
fromFloat2Double = F.float2Double

fromDouble2Float :: Double → Float
fromDouble2Float = F.double2Float

