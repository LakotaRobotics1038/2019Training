#include <Adafruit_NeoPixel.h>
#include "WS2812_Definitions.h"

//defines string
#define PIN 4
#define LED_COUNT 10
Adafruit_NeoPixel leds = Adafruit_NeoPixel(LED_COUNT, PIN, NEO_GRB + NEO_KHZ800);

void setup() 
{
  // put your setup code here, to run once:
  leds.begin();
  Serial.begin(9600);
  while(!Serial){}
}

void loop() 
{
  // put your main code here, to run repeatedly:
  for(int i = 0; i < 193; i++)
  {
    byte pattern = Serial.read();
    switch (pattern)
    {
      case 0:
      clearLEDs();
      break;
      
      case 1:
      cascade(BLUE, TOP_DOWN, 10);
      break;
      
      case 2:
      rainbow(i);
      break;
      
      case 3:
      rotate(BLUE, PURPLE, 5);
      break;
      
    }
  }
}

/*
 * 
 * To anyone who cares enough to go through my code: Below here is just methods.
 * It isn't anything you should have to mess with, but you can copy them for future things
 * 
 */


void cascade(unsigned long color, byte direction, byte wait)
{
  if (direction == TOP_DOWN)                                //"runner" code
  {
    for (int i = 0; i < LED_COUNT; i++)
    {
      clearLEDs();  // Turn off all LEDs
      leds.setPixelColor(i, color);  // Set just this one
      leds.show();
      delay(wait);
    }
  }
  else
  {
    for (int i = LED_COUNT - 1; i >= 0; i--)
    {
      clearLEDs();
      leds.setPixelColor(i, color);
      leds.show();
      delay(wait);
    }
  }
}

void clearLEDs()                                  //set all LEDs to black
{
  for (int i = 0; i < LED_COUNT; i++)
  {
    leds.setPixelColor(i, 0);
  }
}

void rainbow(byte startPosition)                          //rainbow code(AKA the best code)
{
  /*I origionally found this code on google, so if its
    bad, it's not entirely my fault. If you want to see
    the original pseudocode look in the WS2812_Breakout_Example
    file. -M
    We(Marley and I) have fixed this code, and it no longer has the white "runner". 
    The red dot stands out prominently if you're using this on a small string though. -D
    */
  int rainbowScale = 192 / (.25 * LED_COUNT);        //this sets the scale of the rainbow. smaller LED_COUNT = more rainbows. larger LED_COUNT = longer rainbows

  for (int i = 0; i < LED_COUNT; i++)
  {
    int orderNum = (rainbowScale * (i + startPosition)) % 192;  //a number between 0 and 191 that determines where on the rainbow the LED is

    //use this to trouble shoot if the annimation glitches
    /*Serial.print(startPosition);
      Serial.print (" : ");
      Serial.println(orderNum);*/

    leds.setPixelColor(i, rainbowOrder(orderNum));
  }
}

uint32_t rainbowOrder(byte position)
{
  if (position < 31)                      //0-30 => red-yellow
  {
    return leds.Color(0xFF, position * 8, 0);
  }
  else if (position < 63)                 //30-62 => yellow-green
  {
    position -= 31;
    return leds.Color(0xFF - position * 8, 0xFF, 0);
  }
  else if (position < 95)                 //63-94 => green-aqua
  {
    position -= 63;
    return leds.Color(0, 0xFF, position * 8);
  }
  else if (position < 127)                //95-126 => aqua-blue
  {
    position -= 95;
    return leds.Color(0, 0xFF - position * 8, 0xFF);
  }
  else if (position < 159)                //127-158 => blue-purple
  {
    position -= 127;
    return leds.Color(position * 8, 0, 0xFF);
  }
  else if (position < 191)                //159-190 => purple-red
  {
    position -= 159;
    return leds.Color(0xFF, 0x00, 0xFF - position * 8);
  }
  /*when 191 goes into the above statement, it
    makes the LED purple and not red, so we set it
    manually to prevent a "runner"*/
  else                                   //191 => red
  {
    return leds.Color(0xFF, 0x00, 0x00);
  }
}

void rotate (unsigned long colora, unsigned long colorb, byte longshort)    //rotate code
/* I'm sorry this code is poorly written, but it works
  so I'm not going to try to make it more efficent. Sorry*/
{
  for (int i = 0; i < LED_COUNT; i = i + longshort * 2)         //sets LEDs in front of ani pixle
  {
    for (int j = 0; j < longshort; j++)                 //sets first color
    {
      leds.setPixelColor(i + j, colora);
    }
    for (int j = longshort; j < longshort * 2; j++)     //sets second color
    {
      leds.setPixelColor(i + j, colorb);
    }
  }
  for (int i = 0; i > 0; i = i - longshort * 2)                 //sets LEDs behind ani pixle
  {
    for (int j = 0; j < longshort; j++)                //sets first color
    {
      leds.setPixelColor(i - j - 1, colorb);
    }
    for (int j = longshort; j < longshort * 2; j++)   //sets second color
    {
      leds.setPixelColor(i - j - 1, colora);
    }
  }
}
