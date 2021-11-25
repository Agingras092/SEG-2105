1. This should work properly.

2. Supported by following code

	case R.id.buPlusMinus:
                // If a negative is present, remove it. If not, add one.
                if(number.indexOf('-') == -1 )
                    number  = "-" + number;
                else
                    number = number.substring(1);
                break;

Notes - 

 - Although mentioned in the lab to use RelativeLayout or ConstraintLayout, we found it easiest to use GridLayout instead. Hopefully this isnt
	against any sort of ruling, we just used it since it's what we knew. I believe that this led to us not being able to create circular
	buttons, which was a bummer but not a deal breaker in the final stretch.