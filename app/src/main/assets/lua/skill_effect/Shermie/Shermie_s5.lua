--v3
--[[
Shermie
0
0
penxue
1
{default,gongji7,1,0,0,34,1.000000}
1
{default,0.000000,0.000000,-200.000000,0.000000,0,1,0,1,0.000000,0,0,0,0}
0

0

1
{0,Shermie,2,0,0.000000,0.000000,81,0}
1
{default,texiao1,1,0,0,81,1.000000}
1
{default,500.000000,300.000000,500.000000,300.000000,2,2,0,1,0.000000,0,0,0,0}
0

0


daiji
]]--
local skillTest = {
attack_type       = "jingong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"gongji7",1.000000,0,34,1}}, 
pos_sequence      = {{0.000000,0.000000,-200.000000,0.000000,0,1,0,1,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"Shermie",2,0,0.000000,0.000000,{{"texiao1",1.000000,0,81,1}},{{500.000000,300.000000,500.000000,300.000000,2,2,0,1,0.000000,0,0,0,0}},{},{},0}}, 
bloodNum          = 9,
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
