--v3
--[[
Inuyasha
0
0
penxue
1
{default,gongji11,1,0,0,83,1.000000}
1
{default,0.000000,0.000000,-200.000000,0.000000,0,1,0,1,0.000000,0,0,0,0}
0

0

1
{0,Inuyasha,2,0,0.000000,0.000000,83,0}
1
{default,texiao1,1,0,0,83,1.000000}
1
{default,500.000000,150.000000,500.000000,150.000000,2,2,0,1,0.000000,0,0,0,0}
1
{default,0.800000,0.800000,0.800000,0.800000,0,0,1}
0


daiji
]]--
local skillTest = {
attack_type       = "jingong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"gongji11",1.000000,0,83,1}}, 
pos_sequence      = {{0.000000,0.000000,-200.000000,0.000000,0,1,0,1,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"Inuyasha",2,0,0.000000,0.000000,{{"texiao1",1.000000,0,83,1}},{{500.000000,150.000000,500.000000,150.000000,2,2,0,1,0.000000,0,0,0,0}},{{0.800000,0.800000,0.800000,0.800000,0,0,1}},{},0}}, 
bloodNum          = 6,
flyAnim           = "ladeng",
flyAnim_equence   = {{"impact",1}}, 
par               = "",
fly_v             = 100,
fly_h             = 0,
hurtAnim          = "penxue",
hurtAnim_equence  = {{"impact",1}},
skillCallFunc     = function(self,battleSkill,battleTable) 
   local skillData = battleTable.m_currentFrameData; 
   table.foreach(skillData,function(k,v) 
   end); 
   local hpValue = 1;
   local function attackEnd()
   end
   battleSkill:createNormalAttack({skills_table = self,hpValue = skillData.hurt,animEndCallFunc = attackEnd});
end, 
}
return skillTest
